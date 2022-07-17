package com.bruno.pokedex.presentation.ui.detailpokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruno.pokedex.domain.model.PokemonDetail
import com.bruno.pokedex.domain.usecase.GetPokemonDetailUseCase
import com.bruno.pokedex.presentation.ui.detailpokemon.mapper.toPokemonDetailScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
): ViewModel() {

    val uiState = DetailPokemonScreenUiState()
    private var pokemonId = 0

    fun setup(id: Int) {
        if (pokemonId != 0) return
        pokemonId = id
        fetchPokemonDetails()
    }

    private fun fetchPokemonDetails() = viewModelScope.launch {
        getPokemonDetailUseCase.execute(pokemonId)
        val pokemonDetail = getPokemonDetailUseCase.execute(pokemonId).getOrElse {  }
        if (pokemonDetail !is PokemonDetail) return@launch
        uiState.pokemonDetail.value = pokemonDetail.toPokemonDetailScreen()
    }
}