package com.bruno.pokedex.domain.repository

import com.bruno.pokedex.domain.model.PokemonDetail
import com.bruno.pokedex.domain.model.PokemonPaginated

interface PokemonRepository {

    suspend fun getPokemonPaginated(page: Int): Result<PokemonPaginated>
    suspend fun getPokemonDetail(pokemonId: Int): Result<PokemonDetail>
}