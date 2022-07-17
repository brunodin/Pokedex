package com.bruno.pokedex.data.mapper

import com.bruno.pokedex.BuildConfig
import com.bruno.pokedex.data.response.PokemonResponse
import com.bruno.pokedex.domain.model.Pokemon

object PokemonMapper {

    fun PokemonResponse.toPokemon() = Pokemon(
        name = this.name.orEmpty(),
        url = this.url.orEmpty(),
        id = getIdFromPokemon(url.orEmpty()),
        imageUrl = BuildConfig.IMAGE_URL + getIdFromPokemon(url.orEmpty()) + ".png"
    )

    private fun getIdFromPokemon(url: String): Int {
        return if (url.endsWith("/")) {
            url.dropLast(1).takeLastWhile { it.isDigit() }.toInt()
        } else {
            url.takeLastWhile { it.isDigit() }.toInt()
        }
    }
}