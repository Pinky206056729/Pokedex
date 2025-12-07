package repository

import com.siphokazi.pokedex.apiCall.PokeApiService
import com.siphokazi.pokedex.domain.model.PokemonListItem
import com.siphokazi.pokedex.domain.repository.PokemonRepository
import remote.response.PokemonListResponse
import java.util.Locale
import com.siphokazi.pokedex.domain.common.Result
import com.siphokazi.pokedex.domain.model.PokemonDetail
import com.siphokazi.pokedex.domain.model.PokemonStat
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import remote.response.PokemonDetailResponse
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService
) : PokemonRepository {

    // Get List Implementation
    override suspend fun getPokemonList(limit: Int): Result<List<PokemonListItem>> {
        return try {
            val response: PokemonListResponse = apiService.getPokemonList(limit)

            // Use coroutineScope to execute all type lookups in parallel
            val listItemsWithDeferredType = coroutineScope {

                response.results.map { listItemDto ->

                    val id = listItemDto.url.trimEnd('/').substringAfterLast('/').toInt()

                    // Asynchronously start fetching the type for each Pokémon
                    val typeDeferred = async { getPrimaryPokemonType(id) }

                    // Prepare the PokemonListItem model placeholder
                    val listItem = PokemonListItem(
                        id = id,
                        name = listItemDto.name.replaceFirstChar { char ->
                            if (char.isLowerCase()) char.uppercase(Locale.ROOT) else char.toString()
                        },
                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
                        type = ""
                    )

                    // Return the item and the deferred result for later awaiting
                    Pair(listItem, typeDeferred)
                }
            }

            // Await all results and finalize the list with the type data
            val finalPokemonList = listItemsWithDeferredType.map { (listItem, typeDeferred) ->
                val primaryType = typeDeferred.await()

                listItem.copy(
                    type = primaryType.replaceFirstChar { char -> // Capitalize the type name
                        if (char.isLowerCase()) char.uppercase(Locale.ROOT) else char.toString()
                    }
                )
            }

            Result.Success(finalPokemonList)

        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

// Get Detail Implementation
    override suspend fun getPokemonDetail(id: Int): Result<PokemonDetail> {
        return try {
            val response: PokemonDetailResponse = apiService.getPokemonDetail(id)

            // Convert DTO to Domain Model
            val detail: PokemonDetail = response.toDomainModel()

            Result.Success(detail)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    private suspend fun getPrimaryPokemonType(pokemonId: Int): String {

        return try {
            val detailResponse = apiService.getPokemonDetail(pokemonId)

            // Assuming primary type is the first one in the list
            val typeName = detailResponse.types.firstOrNull()?.type?.name
            typeName ?: "Unknown"
        } catch (e: Exception) {
            "Unknown" // Return a safe default on failure
        }
    }
}

/**
 * Extension function to convert the API's detailed response (DTO)
 * into the clean Domain Model (PokemonDetail).
 */

fun PokemonDetailResponse.toDomainModel(): PokemonDetail {
    // API provides height in decimetres (dm) and weight in hectograms (hg).
    // Domain model requires standard SI units (meters, kilograms).

    //Conversion: 1 dm = 0.1 m. Height / 10.0
    val heightMeters = this.height / 10.0f

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