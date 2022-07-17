package com.bruno.pokedex.data.api

import com.bruno.pokedex.data.response.PokemonPaginatedResponse
import retrofit2.http.GET

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonPaginated(): PokemonPaginatedResponse
}