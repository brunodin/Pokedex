package com.bruno.pokedex.data.api

import com.bruno.pokedex.data.response.PokemonDetailResponse
import com.bruno.pokedex.data.response.PokemonPaginatedResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonPaginated(): PokemonPaginatedResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetailResponse
}