package com.siphokazi.pokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int, // Raw height in decimetres (dm)
    val weight: Int, // Raw weight in hectograms (hg)
    val sprites: Sprites,
    val stats: List<StatWrapper>,
    val types: List<TypeWrapper>,
    val abilities: List<AbilityWrapper>
)

data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String?
)

data class StatWrapper(
    @SerializedName("base_stat")
    val baseStat: Int,
    val stat: StatName
)

data class StatName(
    val name: String // "hp", "attack"
)

data class TypeWrapper(
    val slot: Int,
    val type: TypeName
)

data class TypeName(
    val name: String //"fire", "water"
)

data class AbilityWrapper(
    val slot: Int,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val ability: AbilityName // The object containing the ability's name
)

data class AbilityName(
    val name: String //"blaze", "overgrow"
)