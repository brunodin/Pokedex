package com.bruno.pokedex.di

import com.bruno.pokedex.data.api.PokemonApi
import com.bruno.pokedex.data.repository.PokemonRepositoryImpl
import com.bruno.pokedex.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun providePokemonRepository(api: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(api = api)
    }
}