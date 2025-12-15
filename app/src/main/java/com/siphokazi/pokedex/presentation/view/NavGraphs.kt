package com.siphokazi.pokedex.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// Navigation Setup
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "pokemon_list_screen"
    ) {
        // HOME SCREEN (List)
        composable("pokemon_list_screen") {
            HomeScreen(
                onPokemonClick = { pokemonId ->
                    navController.navigate("pokemon_detail_screen/$pokemonId")
                }
            )
        }

        //DETAIL SCREEN
        composable(
            "pokemon_detail_screen/{pokemonId}",
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getInt("pokemonId")

            if (pokemonId != null) {
                // Call the DetailScreen, providing the ID
                DetailScreen(pokemonId = pokemonId)
            }
        }
    }
}