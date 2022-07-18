package com.bruno.pokedex.data.response


import com.google.gson.annotations.SerializedName

data class PokemonPaginatedResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("results")
    val pokemonList: List<PokemonResponse>?
) {
    companion object {
        fun mock() = PokemonPaginatedResponse(
            count = 12,
            pokemonList = listOf(PokemonResponse.mock())
        )
    }
}