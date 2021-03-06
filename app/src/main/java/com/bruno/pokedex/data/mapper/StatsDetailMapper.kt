package com.bruno.pokedex.data.mapper

import com.bruno.pokedex.data.response.StatsDetailResponse
import com.bruno.pokedex.domain.model.PokemonStatsEnum
import com.bruno.pokedex.domain.model.Stats
import com.bruno.pokedex.domain.model.StatsDetail
import com.bruno.pokedex.util.orZero

object StatsDetailMapper {

    fun StatsDetailResponse.toStatsDetail() = StatsDetail(
        baseStat = this.baseStat.orZero(),
        effort = this.effort.orZero(),
        stat = Stats(
            stats = PokemonStatsEnum.fromValue(this.stat?.name),
            url = this.stat?.url.orEmpty()
        )
    )
}