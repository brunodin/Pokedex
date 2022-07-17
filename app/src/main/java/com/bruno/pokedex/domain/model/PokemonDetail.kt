package com.bruno.pokedex.domain.model

data class PokemonDetail(
    val baseExperience: Int,
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val stats: List<StatsDetail>,
    val types: List<TypeDetail>,
    val weight: Int
)
