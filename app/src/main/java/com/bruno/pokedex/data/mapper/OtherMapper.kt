package com.bruno.pokedex.data.mapper

import com.bruno.pokedex.data.mapper.OfficialArtworkMapper.toOfficialArtwork
import com.bruno.pokedex.data.response.OtherResponse
import com.bruno.pokedex.domain.model.Other

object OtherMapper {

    fun OtherResponse?.toOther() = Other(
        officialArtwork = this?.officialArtwork.toOfficialArtwork()
    )
}