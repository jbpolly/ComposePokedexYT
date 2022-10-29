package com.mysticraccoon.composepokedexyt.core.di

import com.mysticraccoon.composepokedexyt.data.PokemonApi
import com.mysticraccoon.composepokedexyt.data.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi): PokemonRepository{
        return PokemonRepository(api)
    }

}