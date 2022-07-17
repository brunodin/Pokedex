package com.bruno.pokedex.presentation.ui.pokemon

import com.bruno.pokedex.domain.model.Pokemon
import com.bruno.pokedex.util.EMPTY_STRING
import kotlinx.coroutines.flow.MutableStateFlow

class PokemonScreenUiState {

    val search = MutableStateFlow(EMPTY_STRING)
    val pokemonList = MutableStateFlow(emptyList<Pokemon>())
    val isLoading = MutableStateFlow(false)
    val isError = MutableStateFlow(false)
    val screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)

    fun onFailure(isFirstPage: () -> Boolean) {
        if (isFirstPage()) screenState.value = ScreenState.Failure else isError.value = true
        isLoading.value = false
    }
    fun onLoading(isFirstPage: () -> Boolean) {
        if (isFirstPage()) screenState.value = ScreenState.Loading else isLoading.value = true
        isError.value = false
    }

    fun onSuccess(pokemonList: List<Pokemon>) {
        this.pokemonList.value = pokemonList
        screenState.value = ScreenState.Success
        isLoading.value = false
        isError.value = false
    }

    sealed class ScreenState {
        object Loading : ScreenState()
        object Success : ScreenState()
        object Failure : ScreenState()
    }
}