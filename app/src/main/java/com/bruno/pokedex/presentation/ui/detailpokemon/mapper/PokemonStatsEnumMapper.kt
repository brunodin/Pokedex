package com.bruno.pokedex.presentation.ui.detailpokemon.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bruno.pokedex.R
import com.bruno.pokedex.domain.model.PokemonStatsEnum
import com.bruno.pokedex.presentation.theme.StatsAtk
import com.bruno.pokedex.presentation.theme.StatsDef
import com.bruno.pokedex.presentation.theme.StatsHp
import com.bruno.pokedex.presentation.theme.StatsSpAtk
import com.bruno.pokedex.presentation.theme.StatsSpDef
import com.bruno.pokedex.presentation.theme.StatsSpd

@Composable
fun PokemonStatsEnum.stats() = when(this) {
    PokemonStatsEnum.HP -> stringResource(id = R.string.pokemon_details_hp)
    PokemonStatsEnum.ATTACK -> stringResource(id = R.string.pokemon_details_atk)
    PokemonStatsEnum.DEFENSE -> stringResource(id = R.string.pokemon_details_def)
    PokemonStatsEnum.SPECIAL_ATTACK -> stringResource(id = R.string.pokemon_details_special_atk)
    PokemonStatsEnum.SPECIAL_DEFENSE -> stringResource(id = R.string.pokemon_details_special_def)
    PokemonStatsEnum.SPEED -> stringResource(id = R.string.pokemon_details_speed)
}

@Composable
fun PokemonStatsEnum.color() = when(this) {
    PokemonStatsEnum.HP -> StatsHp
    PokemonStatsEnum.ATTACK -> StatsAtk
    PokemonStatsEnum.DEFENSE -> StatsDef
    PokemonStatsEnum.SPECIAL_ATTACK -> StatsSpAtk
    PokemonStatsEnum.SPECIAL_DEFENSE -> StatsSpDef
    PokemonStatsEnum.SPEED -> StatsSpd
}