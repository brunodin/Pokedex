package com.bruno.pokedex.domain.model

data class TypeDetail(
    val slot: Int,
    val type: Type
) {
    companion object {
        fun mock() = TypeDetail(
            slot = 1,
            type = Type(
                name = "Fire",
                url = ""
            )
        )
    }
}
