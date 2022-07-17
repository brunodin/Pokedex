package com.bruno.pokedex.domain.usecase

import com.bruno.pokedex.domain.model.Pokemon
import com.bruno.pokedex.domain.model.PokemonPaginated
import com.bruno.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPokemonPaginatedUseCase @Inject constructor(
    private val repository: PokemonRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun execute(
        search: String
    ): Result<PokemonPaginated> = withContext(dispatcher) {
        repository.getPokemonPaginated(search)
    }
}