package mapper

import com.siphokazi.pokedex.data.remote.model.PokemonDetailResponse
import com.siphokazi.pokedex.domain.model.PokemonDetail
import com.siphokazi.pokedex.domain.model.PokemonStat
import java.util.Locale

fun PokemonDetailResponse.toDomainDetail(): PokemonDetail {
    // Height (API uses decimetres [dm], 10 dm = 1 meter)
    val heightMeters = this.height / 10.0f

    // Weight (API uses hectograms [hg], 10 hg = 1 kilogram)
    val weightKg = this.weight / 10.0f

    // Map stats, capitalize the name
    val mappedStats = stats.map { statWrapper ->
        PokemonStat(
            name = statWrapper.stat.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
            },
            value = statWrapper.baseStat
        )
    }

    // Map types
    val mappedTypes = types.map { typeWrapper ->
        typeWrapper.type.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
        }
    }

    // Map abilities
    val mappedAbilities = abilities.map { abilityWrapper ->
        abilityWrapper.ability.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
        }
    }

    return PokemonDetail(
        id = id,
        name = name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
        },
        heightInMeters = heightMeters,
        weightInKg = weightKg,
        frontSpriteUrl = sprites.frontDefault ?: "",
        stats = mappedStats,
        types = mappedTypes,
        abilities = mappedAbilities,
    )
}