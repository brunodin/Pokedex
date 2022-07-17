package com.bruno.pokedex.presentation.ui.detailpokemon

import kotlinx.coroutines.flow.MutableStateFlow

class DetailPokemonScreenUiState {
    val pokemonDetail = MutableStateFlow(PokemonDetailScreenValue())
    val screenValue = MutableStateFlow<ScreenState>(ScreenState.Loading)

    sealed class ScreenState {
        object Loading : ScreenState()
        object Success : ScreenState()
        object Failure : ScreenState()
    }
}