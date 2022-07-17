package com.bruno.pokedex.data.mapper

import com.bruno.pokedex.data.response.TypeDetailResponse
import com.bruno.pokedex.domain.model.Type
import com.bruno.pokedex.domain.model.TypeDetail
import com.bruno.pokedex.util.orZero

object TypeDetailMapper {

    fun TypeDetailResponse.toTypeDetail() = TypeDetail(
        slot = this.slot.orZero(),
        type = Type(
            name = this.type?.name.orEmpty(),
            url = this.type?.url.orEmpty()
        )
    )
}