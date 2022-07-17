package com.bruno.pokedex.domain.model

data class PokemonPaginated(
    val count: Int,
    val pokemonList: List<Pokemon>
)
