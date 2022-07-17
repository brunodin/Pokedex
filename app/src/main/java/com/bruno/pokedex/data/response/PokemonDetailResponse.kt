package com.bruno.pokedex.data.response


import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("base_experience")
    val baseExperience: Int?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("sprites")
    val sprites: SpritesResponse?,
    @SerializedName("stats")
    val stats: List<StatsDetailResponse>?,
    @SerializedName("types")
    val types: List<TypeDetailResponse>?,
    @SerializedName("weight")
    val weight: Int?
)