package com.bruno.pokedex.presentation.ui.detailpokemon

import com.bruno.pokedex.domain.model.PokemonStatsEnum
import com.bruno.pokedex.util.EMPTY_STRING
import com.bruno.pokedex.util.ZERO

data class PokemonDetailScreenValue(
    val name: String = EMPTY_STRING,
    val weight: Float = ZERO,
    val height: Float = ZERO,
    val imageUrl: String = EMPTY_STRING,
    val baseStats: List<BaseStatsScreenValue> = emptyList(),
    val types: List<String> = emptyList()
) {
    companion object {
        fun mock() = PokemonDetailScreenValue(
            name = "Bulbasaur",
            weight = 965.0f,
            height = 15.0f,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            baseStats = listOf(BaseStatsScreenValue.mock()),
            types = listOf("FIRE"),
        )
    }
}

data class BaseStatsScreenValue(
    val stats: PokemonStatsEnum,
    val percentage: Int,
) {
    companion object {
        fun mock() = BaseStatsScreenValue(
            stats = PokemonStatsEnum.HP,
            percentage = 47
        )
    }
}