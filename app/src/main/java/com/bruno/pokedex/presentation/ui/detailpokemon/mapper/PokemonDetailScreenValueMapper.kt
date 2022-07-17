package com.bruno.pokedex.presentation.ui.detailpokemon.mapper

import com.bruno.pokedex.domain.model.PokemonDetail
import com.bruno.pokedex.presentation.ui.detailpokemon.BaseStatsScreenValue
import com.bruno.pokedex.presentation.ui.detailpokemon.PokemonDetailScreenValue
import com.bruno.pokedex.util.firstCharUpperCase

private const val MAX_STATS = 255
private const val ONE_HUNDRED_PERCENTAGE = 100

fun PokemonDetail.toPokemonDetailScreen() = PokemonDetailScreenValue(
    name = this.name.firstCharUpperCase(),
    weight = this.weight,
    height = this.height,
    imageUrl = this.sprites.other.officialArtwork.frontDefault,
    types = this.types.map { it.type.name.uppercase() },
    baseStats = this.stats.map {
        BaseStatsScreenValue(
            stats = it.stat.stats,
            percentage = getBaseStatsInPercentage(it.baseStat)
        )
    },
)

private fun getBaseStatsInPercentage(baseStats: Int) = (baseStats * ONE_HUNDRED_PERCENTAGE) / MAX_STATS