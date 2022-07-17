package com.bruno.pokedex.data.mapper

import com.bruno.pokedex.data.mapper.PokemonMapper.toPokemon
import com.bruno.pokedex.data.response.PokemonPaginatedResponse
import com.bruno.pokedex.domain.model.Pokemon
import com.bruno.pokedex.domain.model.PokemonPaginated
import com.bruno.pokedex.util.orZero

object PokemonPaginatedMapper {

    fun PokemonPaginatedResponse.toPokemonPaginated() = PokemonPaginated(
        count = this.count.orZero(),
        pokemonList = this.pokemonList?.map { it.toPokemon() }.orEmpty(),
    )
}