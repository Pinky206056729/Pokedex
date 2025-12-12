package com.siphokazi.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siphokazi.pokedex.domain.model.PokemonDetail
import com.siphokazi.pokedex.domain.usecase.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import common.Result

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun loadPokemonDetail(pokemonId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = getPokemonDetailUseCase(pokemonId)) {
                is Result.Success<PokemonDetail> -> {
                    _uiState.update { it.copy(pokemon = result.data, isLoading = false) }
                }

                is Result.Failure -> {
                    _uiState.update { it.copy(
                        isLoading = false,
                        error = "Failed to load details: ${result.exceptionOrNull()?.message}"
                    ) }
                }
            }
        }
    }
}

data class DetailUiState(
    val pokemon: PokemonDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
