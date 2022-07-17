package com.bruno.pokedex.data.response


import com.google.gson.annotations.SerializedName

data class StatsResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)