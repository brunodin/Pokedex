package com.bruno.pokedex.data.api

import com.bruno.pokedex.data.response.PokemonDetailResponse
import com.bruno.pokedex.data.response.PokemonPaginatedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonPaginated(@Query("offset") page: Int): PokemonPaginatedResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetailResponse
}