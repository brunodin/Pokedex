package com.bruno.pokedex.data.mapper

import com.bruno.pokedex.data.mapper.SpritesMapper.toSprites
import com.bruno.pokedex.data.mapper.StatsDetailMapper.toStatsDetail
import com.bruno.pokedex.data.mapper.TypeDetailMapper.toTypeDetail
import com.bruno.pokedex.data.response.PokemonDetailResponse
import com.bruno.pokedex.domain.model.PokemonDetail
import com.bruno.pokedex.util.orZero

object PokemonDetailMapper {

    fun PokemonDetailResponse.toPokemonDetail() = PokemonDetail(
        baseExperience = this.baseExperience.orZero(),
        height = this.height.orZero(),
        id = this.id.orZero(),
        name = this.name.orEmpty(),
        sprites = this.sprites.toSprites(),
        stats = this.stats?.map { it.toStatsDetail() }.orEmpty(),
        types = this.types?.map { it.toTypeDetail() }.orEmpty(),
        weight = this.weight.orZero()

    )
}