package com.bruno.pokedex.domain.model

data class Sprites(
    val frontDefault: String,
    val frontShiny: String,
    val other: Other,
) {
    companion object {
        fun mock() = Sprites(
            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            other = Other(
                officialArtwork = OfficialArtwork(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
                )
            )
        )
    }
}
