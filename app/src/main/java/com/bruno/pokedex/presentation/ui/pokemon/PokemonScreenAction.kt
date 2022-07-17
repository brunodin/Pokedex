package com.bruno.pokedex.presentation.ui.pokemon

import com.bruno.pokedex.domain.model.Pokemon

sealed class PokemonScreenAction {
    data class SearchChangedAction(val search: String) : PokemonScreenAction()
    object SearchClickedAction : PokemonScreenAction()
    data class PokemonClickedAction(val pokemon: Pokemon) : PokemonScreenAction()
}