package com.bruno.pokedex.data.response


import com.google.gson.annotations.SerializedName

data class TypeDetailResponse(
    @SerializedName("slot")
    val slot: Int?,
    @SerializedName("type")
    val type: TypeResponse?
) {
    companion object {
        fun mock() = TypeDetailResponse(
            slot = 1,
            type = TypeResponse(
                name = "Fire",
                url = ""
            )
        )
    }
}