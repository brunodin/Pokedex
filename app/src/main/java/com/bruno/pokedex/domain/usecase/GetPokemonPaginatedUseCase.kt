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

    suspend fun execute(page: Int): Result<PokemonPaginated> = withContext(dispatcher) {
        val newOffset = page * OFFSET
        repository.getPokemonPaginated(newOffset)
    }

    private companion object {
        const val OFFSET = 20
    }
}