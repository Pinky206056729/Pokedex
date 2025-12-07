package com.siphokazi.pokedex.domain.model

data class PokemonListItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val type: String
)

data class PokemonDetail(
    val id: Int,
    val name: String,
    val heightInMeters: Float,
    val weightInKg: Float,
    val frontSpriteUrl: String,
    val stats: List<PokemonStat>,
    val types: List<String>,
    val abilities: List<String>
)

data class PokemonStat(
    val name: String,
    val value: Int
)
