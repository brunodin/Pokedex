package com.bruno.pokedex.presentation.ui.detailpokemon.mapper

import com.bruno.pokedex.domain.model.PokemonDetail
import com.bruno.pokedex.presentation.ui.detailpokemon.BaseStatsScreenValue
import com.bruno.pokedex.presentation.ui.detailpokemon.PokemonDetailScreenValue
import com.bruno.pokedex.util.firstCharUpperCase

fun PokemonDetail.toPokemonDetailScreen() = PokemonDetailScreenValue(
    name = this.name.firstCharUpperCase(),
    weight = this.weight.toFloat(),
    height = this.height.toFloat(),
    imageUrl = this.sprites.other.officialArtwork.frontDefault,
    types = this.types.map { it.type.name.uppercase() },
    baseStats = this.stats.map {
        BaseStatsScreenValue(
            stats = it.stat.stats,
            percentage = it.baseStat.toDouble()
        )
    },
)