package com.bruno.pokedex.domain.model

data class StatsDetail(
    val baseStat: Int,
    val effort: Int,
    val stat: Stats
) {
    companion object {
        fun mock() = StatsDetail(
            baseStat = 120,
            effort = 1,
            stat = Stats(
                stats = PokemonStatsEnum.HP,
                url = ""
            )
        )
    }
}
