package com.siphokazi.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siphokazi.pokedex.domain.model.PokemonListItem
import com.siphokazi.pokedex.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.siphokazi.pokedex.domain.common.Result
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Locale

// --- 1. UI State Definition ---
data class HomeUiState(
    val isLoading: Boolean = false,
    // filteredPokemonList is displayed on the UI
    val filteredPokemonList: List<PokemonListItem> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    // STATE FOR SEARCH
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    //  STATE FOR FULL LIST
    private val _fullPokemonList = MutableStateFlow<List<PokemonListItem>>(emptyList())

    // UI State: Combined filtered results
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadPokemon()
        observeSearch() // Start observing changes to the search query
    }

    // Public function to update the search query from the UI
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // Data Loading
    private fun loadPokemon() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = getPokemonListUseCase(limit = 100)) {
                is Result.Success<List<PokemonListItem>> -> {
                    // Store the full list internally
                    _fullPokemonList.value = result.data
                    // The observeSearch combine block will update _uiState with the full list
                }

                is Result.Failure -> {
                    _uiState.update { it.copy(
                        error = "Failed to load Pokémon list: ${result.exceptionOrNull()?.message}",
                        isLoading = false
                    ) }
                }
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    // Search Logic
    private fun observeSearch() {
        combine(_searchQuery, _fullPokemonList) { query, fullList ->
            if (query.isBlank()) {
                fullList // Show full list if query is empty
            } else {
                fullList.filter { pokemon ->
                    val normalizedQuery = query.trim().lowercase(Locale.ROOT)

                    pokemon.name.lowercase(Locale.ROOT).contains(normalizedQuery) ||
                            pokemon.id.toString() == normalizedQuery
                }
            }
        }
            .onEach { filteredList ->
                _uiState.update { it.copy(filteredPokemonList = filteredList) }
            }
            .launchIn(viewModelScope)
    }
}