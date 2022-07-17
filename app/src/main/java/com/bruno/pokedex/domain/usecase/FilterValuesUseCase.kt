package com.bruno.pokedex.domain.usecase

import com.bruno.pokedex.domain.model.Pokemon
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FilterValuesUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun execute(
        pokemonList: List<Pokemon>,
        search: String
    ) = withContext(dispatcher) {
        if (search.isEmpty()) return@withContext pokemonList
        pokemonList.filter { it.name.contains(other = search, ignoreCase = true) }
    }
}