package com.mysticraccoon.composepokedexyt.domain.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Pokemon(
    @Json(name = "abilities")
    val abilities: List<Ability>?,
    @Json(name = "base_experience")
    val baseExperience: Int?,
    @Json(name = "forms")
    val forms: List<Form>?,
    @Json(name = "height")
    val height: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "is_default")
    val isDefault: Boolean?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "order")
    val order: Int?,
    @Json(name = "species")
    val species: Species?,
    @Json(name = "sprites")
    val sprites: Sprites?,
    @Json(name = "stats")
    val stats: List<Stat>?,
    @Json(name = "types")
    val types: List<Type>?,
    @Json(name = "weight")
    val weight: Int?
)