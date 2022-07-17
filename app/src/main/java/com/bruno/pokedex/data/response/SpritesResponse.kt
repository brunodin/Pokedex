package com.bruno.pokedex.data.response


import com.google.gson.annotations.SerializedName

data class SpritesResponse(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?,
    @SerializedName("other")
    val other: OtherResponse?,
)