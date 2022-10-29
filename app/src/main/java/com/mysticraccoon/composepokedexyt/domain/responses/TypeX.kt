package com.mysticraccoon.composepokedexyt.domain.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class TypeX(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)