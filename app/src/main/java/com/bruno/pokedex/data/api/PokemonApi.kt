package com.bruno.pokedex.data.api

import com.bruno.pokedex.data.response.PokemonPaginatedResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon/{name}")
    suspend fun getPokemonPaginated(@Path("name") search: String): PokemonPaginatedResponse
}