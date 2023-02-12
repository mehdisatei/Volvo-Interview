package mehdi.satei.volvo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import mehdi.satei.volvo.presentation.screen.details.WeatherDetailsScreen
import mehdi.satei.volvo.presentation.screen.home.HomeScreen

const val WEATHER_DETAILS_ARGUMENT_KEY = "weatherId"

@Composable
fun NavGraph(
    toggleTheme: () -> Unit
) {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        composable(
            Screen.Home.route,
        ) {
            HomeScreen(
                navHostController = navHostController,
                toggleTheme = toggleTheme
            )
        }

        composable(
            Screen.WeatherDetails.route,
            arguments = listOf(navArgument(WEATHER_DETAILS_ARGUMENT_KEY) {
                type = NavType.StringType
            }
            )
        ) {
            it.arguments?.getString(WEATHER_DETAILS_ARGUMENT_KEY)?.let {
                WeatherDetailsScreen(id = it)
            }
        }
    }
}

