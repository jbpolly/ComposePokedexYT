package com.mysticraccoon.composepokedexyt.domain.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Type(
    @Json(name = "slot")
    val slot: Int?,
    @Json(name = "type")
    val type: TypeX?
)