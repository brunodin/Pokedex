package com.bruno.pokedex.presentation.ui.detailpokemon

import com.bruno.pokedex.util.EMPTY_STRING
import kotlinx.coroutines.flow.MutableStateFlow

class DetailPokemonScreenUiState {
    val name = MutableStateFlow(EMPTY_STRING)
    val image = MutableStateFlow(EMPTY_STRING)
}