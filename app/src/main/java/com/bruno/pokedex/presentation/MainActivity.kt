package com.bruno.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bruno.pokedex.presentation.ui.detailpokemon.DetailPokemonScreen
import com.bruno.pokedex.presentation.ui.pokemon.PokemonScreen
import com.bruno.pokedex.util.orZero
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Navigation.RoutePokemon.router,
                builder = {
                    composable(route = Navigation.RoutePokemon.router) {
                        PokemonScreen(
                            onNextScreen = { pokemon ->
                                navController.navigate(route = Navigation.RouteDetail.router + pokemon.id)
                            }
                        )
                    }
                    composable(
                        route = Navigation.RouteDetail.router + "{$POKEMON_ID}",
                        arguments = listOf(navArgument(POKEMON_ID){
                            type = NavType.IntType
                        })
                    ) {
                        val id = it.arguments?.getInt(POKEMON_ID).orZero()
                        DetailPokemonScreen(pokemonId = id)
                    }
                }
            )
        }
    }

    sealed class Navigation(val router: String) {
        object RoutePokemon : Navigation(router = "route-pokemon")
        object RouteDetail : Navigation(router = "route-detail/")
    }

    private companion object {
        const val POKEMON_ID = "pokemonId"
    }
}