package com.bruno.pokedex.presentation.ui.detailpokemon

import kotlinx.coroutines.flow.MutableStateFlow

class DetailPokemonScreenUiState {
    val pokemonDetail = MutableStateFlow(PokemonDetailScreenValue())
    val screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)

    fun onLoading() {
        screenState.value = ScreenState.Loading
    }
    fun onSuccess(pokemonDetail: PokemonDetailScreenValue) {
        this.pokemonDetail.value = pokemonDetail
        screenState.value = ScreenState.Success
    }
    fun onFailure() {
        screenState.value = ScreenState.Failure
    }

    sealed class ScreenState {
        object Loading : ScreenState()
        object Success : ScreenState()
        object Failure : ScreenState()
    }
}