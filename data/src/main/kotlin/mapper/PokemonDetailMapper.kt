package mapper

import remote.response.PokemonDetailResponse
import com.siphokazi.pokedex.domain.model.PokemonDetail
import com.siphokazi.pokedex.domain.model.PokemonStat
import java.util.Locale

/**
 * Extension function to convert the API's detailed response (DTO)
 * into the clean Domain Model (PokemonDetail).
 */

fun PokemonDetailResponse.toDomainModel(): PokemonDetail {
    // API provides height in decimetres (dm) and weight in hectograms (hg).
    // Domain model requires standard SI units (meters, kilograms).

    // Height (API uses decimetres [dm], 10 dm = 1 meter)
    //Conversion: 1 dm = 0.1 m. Height / 10.0
    val heightMeters = this.height / 10.0f

    // Weight (API uses hectograms [hg], 10 hg = 1 kilogram)
    // Conversion: 1 hg = 0.1 kg. Weight / 10.0
    val weightKg = this.weight / 10.0f

    // Use the official front sprite URL, with a fallback if null
    val spriteUrl = this.sprites.front_default
        ?: "https://defaultimage.png" // Fallback image if null

    val mappedStats = this.stats.map { statDto ->
        PokemonStat(
            name = statDto.stat.name
                .replace("-", " ")
                .replaceFirstChar { char -> char.uppercaseChar().toString() },
            value = statDto.base_stat
        )
    }

    val mappedTypes = this.types.map { typeWrapper ->
        typeWrapper.type.name.replaceFirstChar { char -> char.uppercaseChar().toString() }
    }

    val mappedAbilities = this.abilities.map { abilityWrapper ->
        abilityWrapper.ability.name.replaceFirstChar { char -> char.uppercaseChar().toString() }
    }

    return PokemonDetail(
        id = this.id,
        // Capitalize name
        name = this.name.replaceFirstChar { char -> char.uppercase(Locale.ROOT) },
        heightInMeters = heightMeters,
        weightInKg = weightKg,
        frontSpriteUrl = spriteUrl,
        stats = mappedStats,
        types = mappedTypes,
        abilities = mappedAbilities
    )
}