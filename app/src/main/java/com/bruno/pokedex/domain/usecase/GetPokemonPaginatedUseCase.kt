package com.bruno.pokedex.domain.usecase

import com.bruno.pokedex.domain.model.PokemonPaginated
import com.bruno.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetPokemonPaginatedUseCase @Inject constructor(
    private val repository: PokemonRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun execute(): Result<PokemonPaginated> = withContext(dispatcher) {
        repository.getPokemonPaginated()
    }
}