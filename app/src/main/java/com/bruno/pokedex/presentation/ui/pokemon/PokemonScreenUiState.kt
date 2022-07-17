package com.bruno.pokedex.presentation.ui.pokemon

import com.bruno.pokedex.domain.model.Pokemon
import com.bruno.pokedex.util.EMPTY_STRING
import kotlinx.coroutines.flow.MutableStateFlow

class PokemonScreenUiState {

    val search = MutableStateFlow(EMPTY_STRING)
    val pokemonList = MutableStateFlow(emptyList<Pokemon>())
    val isLoading = MutableStateFlow(false)
    val screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)

    fun onFailure(isFirstPage: () -> Boolean) {
        screenState.value = ScreenState.Failure
        isLoading.value = true
    }
    fun onLoading(isFirstPage: () -> Boolean) {
        if (isFirstPage()) screenState.value = ScreenState.Loading else isLoading.value = true
    }

    fun onSuccess(pokemonList: List<Pokemon>) {
        this.pokemonList.value = pokemonList
        screenState.value = ScreenState.Success
        isLoading.value = false
    }

    sealed class ScreenState {
        object Loading : ScreenState()
        object Success : ScreenState()
        object Failure : ScreenState()
    }
}