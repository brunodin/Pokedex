package com.bruno.pokedex.presentation.ui.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruno.pokedex.domain.model.Pokemon
import com.bruno.pokedex.domain.model.PokemonPaginated
import com.bruno.pokedex.domain.usecase.FilterValuesUseCase
import com.bruno.pokedex.domain.usecase.GetPokemonPaginatedUseCase
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.EndReachedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.PokemonClickedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.SearchChangedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreenAction.SearchClickedAction
import com.bruno.pokedex.presentation.ui.pokemon.PokemonViewModel.ScreenEvent
import com.bruno.pokedex.presentation.ui.pokemon.PokemonViewModel.ScreenEvent.NavigateToScreen
import com.bruno.pokedex.util.EMPTY_STRING
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
    private var count = FIRST_PAGE
    private var page = FIRST_PAGE
    private var isInSearch = EMPTY_STRING
    private var pokemonList = mutableListOf<Pokemon>()

    fun setup() {
        if (count != FIRST_PAGE) return
        performFirstPaginatedList()
    }

    fun onEvent(action: PokemonScreenAction) {
        when(action) {
            is PokemonClickedAction -> viewModelScope.sendEvent(NavigateToScreen(action.pokemon))
            SearchClickedAction -> filterPokemon()
            is SearchChangedAction -> performUpdateSearch(action.search)
            EndReachedAction -> performNextPaginationRequest()
        }
    }

    private fun performUpdateSearch(search: String) {
        isInSearch = search
        uiState.search.value = search
    }

    private fun filterPokemon() = viewModelScope.launch {
        val search = uiState.search.value
        val pokemonList = filterValuesUseCase.execute(pokemonList, search)
        uiState.onSuccess(pokemonList.toList())
    }

    private fun performFirstPaginatedList() {
        pokemonList.clear()
        fetchGetPokemonPaginated()
    }

    private fun performNextPaginationRequest() {
        val isLoading = uiState.isLoading.value
        if (pokemonList.size >= count || isLoading || isInSearch.isNotEmpty()) return
        fetchGetPokemonPaginated()
    }

    private fun fetchGetPokemonPaginated() = viewModelScope.launch {
        uiState.onLoading(::checkPageIsFirst)
        val pokemonPaginated = getPokemonUseCase.execute(page).getOrElse { uiState.onFailure(::checkPageIsFirst) }
        if (pokemonPaginated !is PokemonPaginated) return@launch
        count = pokemonPaginated.count
        page = page.inc()
        pokemonList.addAll(pokemonPaginated.pokemonList)
        uiState.onSuccess(pokemonList.toList())
    }

    private fun checkPageIsFirst() = page == FIRST_PAGE

    sealed class ScreenEvent {
        data class NavigateToScreen(val pokemon: Pokemon) : ScreenEvent()
    }

    private companion object {
        const val FIRST_PAGE = 0
    }
}