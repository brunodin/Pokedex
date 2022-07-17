package com.bruno.pokedex.domain.repository

import com.bruno.pokedex.domain.model.PokemonPaginated

interface PokemonRepository {

    suspend fun getPokemonPaginated(search: String): Result<PokemonPaginated>
}