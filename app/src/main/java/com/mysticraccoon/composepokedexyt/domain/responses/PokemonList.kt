package com.mysticraccoon.composepokedexyt.domain.responses


import com.squareup.moshi.Json

data class PokemonList(
    @Json(name = "count")
    val count: Int,
    @Json(name = "next")
    val next: String? = "",
    @Json(name = "previous")
    val previous: String? = "",
    @Json(name = "results")
    val pokemonResults: List<PokemonResult>
)