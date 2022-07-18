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
) {
    companion object {
        fun mock() = PokemonDetailResponse(
            baseExperience = 123,
            height = 15,
            id = 1,
            name = "Bulbasaur",
            sprites = SpritesResponse.mock(),
            stats = listOf(StatsDetailResponse.mock()),
            types = listOf(TypeDetailResponse.mock()),
            weight = 965
        )
    }
}