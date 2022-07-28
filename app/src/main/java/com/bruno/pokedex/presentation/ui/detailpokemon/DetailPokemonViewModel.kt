package com.bruno.pokedex.presentation.ui.detailpokemon

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruno.pokedex.domain.model.PokemonDetail
import com.bruno.pokedex.domain.usecase.GetPokemonDetailUseCase
import com.bruno.pokedex.presentation.ui.detailpokemon.DetailPokemonScreenAction.BackClickedAction
import com.bruno.pokedex.presentation.ui.detailpokemon.DetailPokemonScreenAction.CloseClickedAction
import com.bruno.pokedex.presentation.ui.detailpokemon.DetailPokemonScreenAction.RetryClickedAction
import com.bruno.pokedex.presentation.ui.detailpokemon.DetailPokemonViewModel.ScreenEvent
import com.bruno.pokedex.presentation.ui.detailpokemon.mapper.toPokemonDetailScreen
import com.bruno.pokedex.util.EventFlow
import com.bruno.pokedex.util.EventFlowImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel(), EventFlow<ScreenEvent> by EventFlowImpl() {

    val uiState = DetailPokemonScreenUiState()
    private var pokemonId = FIRST_ID

    fun setup(id: Int) {
        if (pokemonId != FIRST_ID) return
        pokemonId = id
        fetchPokemonDetails()
    }

    fun onEvent(action: DetailPokemonScreenAction) {
        when (action) {
            BackClickedAction -> viewModelScope.sendEvent(ScreenEvent.GoBack)
            CloseClickedAction -> viewModelScope.sendEvent(ScreenEvent.Finish)
            RetryClickedAction -> fetchPokemonDetails()
        }
    }

    private fun fetchPokemonDetails() = viewModelScope.launch {
        uiState.onLoading()
        getPokemonDetailUseCase.execute(pokemonId).fold(
            onSuccess = { uiState.onSuccess(it.toPokemonDetailScreen()) },
            onFailure = { uiState.onFailure() }
        )
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun setupId(id: Int) {
        pokemonId = id
    }

    sealed class ScreenEvent {
        object GoBack : ScreenEvent()
        object Finish : ScreenEvent()
    }

    private companion object {
        const val FIRST_ID = 0
    }
}