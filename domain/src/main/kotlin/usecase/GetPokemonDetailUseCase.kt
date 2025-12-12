package com.siphokazi.pokedex.domain.usecase

import common.Result
import com.siphokazi.pokedex.domain.model.PokemonDetail
import com.siphokazi.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject


class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    // The 'invoke' operator allows the use case to be called like a function: useCase()
    suspend operator fun invoke(pokemonId: Int): Result<PokemonDetail> {
        return repository.getPokemonDetail(pokemonId)
    }
}