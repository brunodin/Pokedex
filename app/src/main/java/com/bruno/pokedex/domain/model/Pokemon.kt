package com.bruno.pokedex.domain.model

import com.bruno.pokedex.BuildConfig

data class Pokemon(
    val name: String,
    val url: String,
    val id: Int,
    val imageUrl: String
) {
    companion object {
        fun mock() = Pokemon(
            name = "Bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/1/",
            id = 1,
            imageUrl = BuildConfig.IMAGE_URL + 1 + ".png"
        )
    }
}