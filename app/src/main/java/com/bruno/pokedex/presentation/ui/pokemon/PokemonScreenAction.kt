package com.bruno.pokedex.presentation.ui.pokemon

import com.bruno.pokedex.domain.model.Pokemon

sealed class PokemonScreenAction {
    data class SearchChangedAction(val search: String) : PokemonScreenAction()
    data class PokemonClickedAction(val pokemon: Pokemon) : PokemonScreenAction()
    object SearchClickedAction : PokemonScreenAction()
    object EndReachedAction : PokemonScreenAction()
    object RetryClickedAction : PokemonScreenAction()
    object CloseClickedAction : PokemonScreenAction()
}