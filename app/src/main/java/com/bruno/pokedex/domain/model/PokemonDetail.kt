package com.bruno.pokedex.domain.model

data class PokemonDetail(
    val baseExperience: Int,
    val height: Float,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val stats: List<StatsDetail>,
    val types: List<TypeDetail>,
    val weight: Float
) {
    companion object {
        fun mock() = PokemonDetail(
            baseExperience = 123,
            height = 15.0f,
            id = 1,
            name = "Bulbasaur",
            sprites = Sprites.mock(),
            stats = listOf(StatsDetail.mock()),
            types = listOf(TypeDetail.mock()),
            weight = 965f
        )
    }
}
