package com.bruno.pokedex.data.response


import com.google.gson.annotations.SerializedName

data class TypeDetailResponse(
    @SerializedName("slot")
    val slot: Int?,
    @SerializedName("type")
    val type: TypeResponse?
)