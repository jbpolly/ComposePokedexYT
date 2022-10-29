package com.mysticraccoon.composepokedexyt.domain.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Sprites(
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_female")
    val backFemale: String?,
    @Json(name = "back_shiny")
    val backShiny: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?,
)