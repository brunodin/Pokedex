package com.bruno.pokedex.presentation.ui.detailpokemon

sealed class DetailPokemonScreenAction {
    object BackClickedAction : DetailPokemonScreenAction()
    object CloseClickedAction : DetailPokemonScreenAction()
    object RetryClickedAction : DetailPokemonScreenAction()
}
