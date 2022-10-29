package com.mysticraccoon.composepokedexyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mysticraccoon.composepokedexyt.ui.pokemondetail.PokemonDetailScreen
import com.mysticraccoon.composepokedexyt.ui.pokemonlist.PokemonListScreen
import com.mysticraccoon.composepokedexyt.ui.theme.ComposePokedexYTTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePokedexYTTheme {

                //navhost which is just a container which contains our different screens and responsible to replace them when we navigate to another screen
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pokemon_list_screen"){
                    composable("pokemon_list_screen"){
                        //put all composables we want in this first screen
                        PokemonListScreen(navController = navController)
                    }
                    composable("pokemon_details_screen/{dominantColor}/{pokemonName}", arguments = listOf(
                        navArgument("dominantColor"){
                            type = NavType.IntType
                        },
                        navArgument("pokemonName"){
                            type = NavType.StringType
                        }
                    )){
                        //composable to the details screen
                        val dominantColor = remember {
                            val color = it.arguments?.getInt("dominantColor")
                            color?.let {
                                Color(it)
                            } ?: Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString("pokemonName")
                        }
                        PokemonDetailScreen(
                            dominantColor = dominantColor,
                            pokemonName = pokemonName?.toLowerCase(Locale.ROOT).orEmpty(),
                            navController = navController
                        )
                    }
                }

            }
        }
    }
}
