package com.bruno.pokedex.data.repository

import com.bruno.pokedex.data.api.PokemonApi
import com.bruno.pokedex.data.mapper.PokemonDetailMapper.toPokemonDetail
import com.bruno.pokedex.data.mapper.PokemonPaginatedMapper.toPokemonPaginated
import com.bruno.pokedex.domain.model.PokemonDetail
import com.bruno.pokedex.domain.model.PokemonPaginated
import com.bruno.pokedex.domain.repository.PokemonRepository
import com.bruno.pokedex.util.safeRequest
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokemonRepository {

    override suspend fun getPokemonPaginated(page: Int): Result<PokemonPaginated> =
        safeRequest(coroutineDispatcher) {
            api.getPokemonPaginated(page).toPokemonPaginated()
        }

    override suspend fun getPokemonDetail(pokemonId: Int): Result<PokemonDetail> =
        safeRequest(coroutineDispatcher) {
            api.getPokemonDetail(pokemonId).toPokemonDetail()
        }
}