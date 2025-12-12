package repository

import com.siphokazi.pokedex.apiCall.PokeApiService
import com.siphokazi.pokedex.domain.model.PokemonListItem
import com.siphokazi.pokedex.domain.repository.PokemonRepository
import remote.response.PokemonListResponse
import java.util.Locale
import common.Result
import com.siphokazi.pokedex.domain.model.PokemonDetail
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import mapper.toDomainModel
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
            //start a background task to fetch that Pokémon’s primary type ("Fire", "Water", "Grass").
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