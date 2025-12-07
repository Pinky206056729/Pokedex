package com.siphokazi.pokedex.di

import com.siphokazi.pokedex.apiCall.PokeApiService
import repository.PokemonRepositoryImpl
import com.siphokazi.pokedex.domain.repository.PokemonRepository
import com.siphokazi.pokedex.domain.usecase.GetPokemonListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Retrofit/API Service Provider
    @Provides
    @Singleton
    fun providePokeApiService(): PokeApiService {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }

    //Repository Implementation Binding
    @Provides
    @Singleton
    fun providePokemonRepository(apiService: PokeApiService): PokemonRepository {
        return PokemonRepositoryImpl(apiService)
    }

    // Use Case Provider
    @Provides
    fun provideGetPokemonListUseCase(repository: PokemonRepository): GetPokemonListUseCase {
        return GetPokemonListUseCase(repository)
    }
}