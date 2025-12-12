package com.siphokazi.pokedex.domain.repository

import com.siphokazi.pokedex.domain.model.PokemonDetail
import com.siphokazi.pokedex.domain.model.PokemonListItem
import common.Result


interface PokemonRepository {
    suspend fun getPokemonList(limit: Int): Result<List<PokemonListItem>>
    suspend fun getPokemonDetail(id: Int): Result<PokemonDetail>
}