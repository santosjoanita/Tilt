package intro.sensors_04_multimedia.tiltjoanasantos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import intro.sensors_04_multimedia.tiltjoanasantos.ui.screens.CountdownScreen
import intro.sensors_04_multimedia.tiltjoanasantos.ui.screens.GameScreen
import intro.sensors_04_multimedia.tiltjoanasantos.ui.screens.MenuScreen
import intro.sensors_04_multimedia.tiltjoanasantos.ui.screens.ResultsScreen
import intro.sensors_04_multimedia.tiltjoanasantos.ui.theme.Tilt_JoanaSantosTheme
import intro.sensors_04_multimedia.tiltjoanasantos.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tilt_JoanaSantosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val gameViewModel: GameViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = "menu"
                    ) {
                        composable("menu") {
                            MenuScreen { id ->
                                // Alterado: Navega primeiro para o Countdown
                                navController.navigate("countdown/$id")
                            }
                        }

                        composable(
                            route = "countdown/{categoryId}",
                            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt("categoryId") ?: 1
                            CountdownScreen(id, navController)
                        }

                        composable(
                            route = "game/{categoryId}",
                            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt("categoryId") ?: 1
                            GameScreen(id, navController, gameViewModel)
                        }

                        composable("results") {
                            ResultsScreen(navController, gameViewModel)
                        }
                    }
                }
            }
        }
    }
}