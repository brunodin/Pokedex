package com.bruno.pokedex.presentation.ui.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruno.pokedex.domain.model.PokemonPaginated
import com.bruno.pokedex.domain.usecase.GetPokemonPaginatedUseCase
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenUiState.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonPaginatedUseCase
) : ViewModel() {

    val uiState = PokemonScreenUiState()
    private var count = 0

    fun setup() {
        fetchGetPokemonPaginated()
    }

    private fun fetchGetPokemonPaginated() = viewModelScope.launch {
        val search = uiState.search.value
        uiState.screenState.value = ScreenState.Loading
        val pokemonPaginated = getPokemonUseCase.execute(search).getOrElse { uiState.onFailure() }
        uiState.onSuccess((pokemonPaginated as PokemonPaginated).pokemonList)
        count = pokemonPaginated.count
    }

    sealed class ScreenEvent {}
}