package com.siphokazi.pokedex.domain.usecase

import com.siphokazi.pokedex.domain.model.PokemonListItem
import com.siphokazi.pokedex.domain.repository.PokemonRepository
import com.siphokazi.pokedex.domain.common.Result
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    // The 'invoke' operator allows the use case to be called like a function: useCase()
    suspend operator fun invoke(limit: Int = 100): Result<List<PokemonListItem>> {
        return repository.getPokemonList(limit)
    }
}