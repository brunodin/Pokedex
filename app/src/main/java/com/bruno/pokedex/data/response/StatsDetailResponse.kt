package com.bruno.pokedex.data.response


import com.google.gson.annotations.SerializedName

data class StatsDetailResponse(
    @SerializedName("base_stat")
    val baseStat: Int?,
    @SerializedName("effort")
    val effort: Int?,
    @SerializedName("stat")
    val stat: StatsResponse?
) {
    companion object {
        fun mock() = StatsDetailResponse(
            baseStat = 120,
            effort = 1,
            stat = StatsResponse(
                name = "hp",
                url = ""
            )
        )
    }
}