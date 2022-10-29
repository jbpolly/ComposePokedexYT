package com.mysticraccoon.composepokedexyt.data.repository

import com.mysticraccoon.composepokedexyt.core.utils.Resource
import com.mysticraccoon.composepokedexyt.data.PokemonApi
import com.mysticraccoon.composepokedexyt.domain.responses.Pokemon
import com.mysticraccoon.composepokedexyt.domain.responses.PokemonList
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val api: PokemonApi){

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>{
        val response = try {
            api.getPokemonList(limit, offset)
        }catch (e: Exception){
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>{
        val response = try {
            api.getPokemonInfo(pokemonName)
        }catch (e: Exception){
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }

}