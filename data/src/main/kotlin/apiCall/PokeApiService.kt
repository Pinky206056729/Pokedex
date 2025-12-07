package com.siphokazi.pokedex.apiCall

import remote.response.PokemonDetailResponse
import remote.response.PokemonListResponse

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int): PokemonListResponse // Server DTO

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetailResponse // Server DTO
}