package mehdi.satei.volvo.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object WeatherDetails : Screen("weather_details_screen/{${WEATHER_DETAILS_ARGUMENT_KEY}}") {
        fun passWeatherId(weatherId: String) = "weather_details_screen/$weatherId"
    }
}
