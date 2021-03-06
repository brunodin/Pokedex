package com.bruno.pokedex.domain.model

data class PokemonPaginated(
    val count: Int,
    val pokemonList: List<Pokemon>
) {
    companion object {
        fun mock() = PokemonPaginated(
            count = 12,
            pokemonList = listOf(Pokemon.mock())
        )
    }
}
