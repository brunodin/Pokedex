package com.bruno.pokedex.domain.model

enum class PokemonStatsEnum(val values: String) {
    HP("hp"),
    ATTACK("attack"),
    DEFENSE("defense"),
    SPECIAL_ATTACK("special-attack"),
    SPECIAL_DEFENSE("special-defense"),
    SPEED("speed");

    companion object {
        fun fromValue(values: String?) = values().find { it.values == values } ?: HP
    }
}