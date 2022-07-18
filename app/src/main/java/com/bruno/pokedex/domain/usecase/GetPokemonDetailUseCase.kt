package com.bruno.pokedex.domain.usecase

import com.bruno.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun execute(pokemonId: Int) = withContext(dispatcher) {
        val result = repository.getPokemonDetail(pokemonId)
        val pokemonDetail = result.getOrNull() ?: return@withContext result
        val newPokemonDetail = pokemonDetail.copy(
            height = convertValue(pokemonDetail.height),
            weight = convertValue(pokemonDetail.weight)
        )
        Result.success(newPokemonDetail)
    }

    private fun convertValue(value: Float)  = (value * 100f).roundToInt() / 1000f
}