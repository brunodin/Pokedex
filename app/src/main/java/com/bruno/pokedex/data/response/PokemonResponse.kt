package com.bruno.pokedex.data.response


import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
) {
    companion object {
        fun mock() = PokemonResponse(
            name = "Bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/1/"
        )
    }
}