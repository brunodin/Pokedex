package com.bruno.pokedex.data.mapper

import com.bruno.pokedex.data.mapper.OtherMapper.toOther
import com.bruno.pokedex.data.response.SpritesResponse
import com.bruno.pokedex.domain.model.Sprites

object SpritesMapper {

    fun SpritesResponse?.toSprites() = Sprites(
        frontDefault = this?.frontDefault.orEmpty(),
        frontShiny = this?.frontShiny.orEmpty(),
        other = this?.other.toOther()
    )
}