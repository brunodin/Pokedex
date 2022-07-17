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
)

data class BaseStatsScreenValue(
    val stats: PokemonStatsEnum,
    val percentage: Double,
)