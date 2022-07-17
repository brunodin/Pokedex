package com.bruno.pokedex.presentation.ui.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruno.pokedex.domain.model.Pokemon
import com.bruno.pokedex.domain.model.PokemonPaginated
import com.bruno.pokedex.domain.usecase.FilterValuesUseCase
import com.bruno.pokedex.domain.usecase.GetPokemonPaginatedUseCase
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.PokemonClickedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.SearchChangedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.SearchClickedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenUiState.ScreenState
import com.bruno.pokedex.presentation.ui.pokemon.PokemonViewModel.ScreenEvent
import com.bruno.pokedex.presentation.ui.pokemon.PokemonViewModel.ScreenEvent.NavigateToScreen
import com.bruno.pokedex.util.EventFlow
import com.bruno.pokedex.util.EventFlowImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonPaginatedUseCase,
    private val filterValuesUseCase: FilterValuesUseCase,
) : ViewModel(), EventFlow<ScreenEvent> by EventFlowImpl()  {

    val uiState = PokemonScreenUiState()
    private var count = 0
    private var pokemonList = emptyList<Pokemon>()

    fun setup() {
        fetchGetPokemonPaginated()
    }

    fun onEvent(action: PokemonScreenAction) {
        when(action) {
            is PokemonClickedAction -> viewModelScope.sendEvent(NavigateToScreen(action.pokemon))
            SearchClickedAction -> filterPokemon()
            is SearchChangedAction -> updateSearchValue(action.search)
        }
    }

    private fun updateSearchValue(search: String) {
        uiState.search.value = search
    }

    private fun filterPokemon() = viewModelScope.launch {
        val search = uiState.search.value
        val pokemonList = filterValuesUseCase.execute(pokemonList, search)
        uiState.onSuccess(pokemonList)

    }

    private fun fetchGetPokemonPaginated() = viewModelScope.launch {
        uiState.screenState.value = ScreenState.Loading
        val pokemonPaginated = getPokemonUseCase.execute().getOrElse { uiState.onFailure() }
        if (pokemonPaginated !is PokemonPaginated) return@launch
        uiState.onSuccess(pokemonPaginated.pokemonList)
        count = pokemonPaginated.count
        pokemonList = pokemonPaginated.pokemonList
    }

    sealed class ScreenEvent {
        data class NavigateToScreen(val pokemon: Pokemon) : ScreenEvent()
    }
}