package com.mysticraccoon.composepokedexyt.data

import com.mysticraccoon.composepokedexyt.domain.responses.Pokemon
import com.mysticraccoon.composepokedexyt.domain.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(@Path("name") name: String): Pokemon
}