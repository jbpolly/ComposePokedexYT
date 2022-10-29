package com.mysticraccoon.composepokedexyt.domain.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Stat(
    @Json(name = "base_stat")
    val baseStat: Int?,
    @Json(name = "effort")
    val effort: Int?,
    @Json(name = "stat")
    val stat: StatX?
)