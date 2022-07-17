package com.bruno.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@InstallIn(SingletonComponent::class)
@Module
class DispatchModule {

    @Provides
    fun provideDispatch(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}
