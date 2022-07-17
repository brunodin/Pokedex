package com.bruno.pokedex.data.mapper

import com.bruno.pokedex.data.response.OfficialArtworkResponse
import com.bruno.pokedex.domain.model.OfficialArtwork

object OfficialArtworkMapper {

    fun OfficialArtworkResponse?.toOfficialArtwork() = OfficialArtwork(
        frontDefault = this?.frontDefault.orEmpty()
    )
}