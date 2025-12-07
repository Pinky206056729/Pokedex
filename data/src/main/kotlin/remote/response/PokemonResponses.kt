package remote.response

data class NameUrlResponse(
    val name: String,
    val url: String
)

/**
 * Maps the response for the main Pokémon list endpoint.
 */
data class PokemonListResponse(
    val results: List<NameUrlResponse>
)

/**
 * The main DTO for the detailed Pokémon endpoint (e.g., /pokemon/1).
 */
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int, // API unit: Decimetres (dm)
    val weight: Int, // API unit: Hectograms (hg)
    val sprites: SpritesResponse,
    val stats: List<StatResponse>,
    val types: List<TypeResponse>,
    val abilities: List<AbilityResponse>,
)

/**
 * Nested DTO for image URLs.
 */
data class SpritesResponse(
    val front_default: String?
)

/**
 * Nested DTO for a specific stat value and its name.
 */
data class StatResponse(
    val base_stat: Int,
    val effort: Int,
    val stat: NameUrlResponse
)

/**
 * Nested DTO for Pokémon types.
 */
data class TypeResponse(
    val slot: Int,
    val type: NameUrlResponse
)

/**
 * Nested DTO for Pokémon abilities.
 */
data class AbilityResponse(
    val is_hidden: Boolean,
    val slot: Int,
    val ability: NameUrlResponse
)
