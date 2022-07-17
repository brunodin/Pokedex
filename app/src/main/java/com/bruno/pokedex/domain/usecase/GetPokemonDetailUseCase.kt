package com.bruno.pokedex.domain.usecase

import com.bruno.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetPokemonDetailUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val repository: PokemonRepository
) {

    suspend fun execute(pokemonId: Int) = withContext(dispatcher) {
        repository.getPokemonInfo(pokemonId)
    }
}