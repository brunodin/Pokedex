package com.bruno.pokedex.data.response


import com.google.gson.annotations.SerializedName

data class OtherResponse(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkResponse?
)