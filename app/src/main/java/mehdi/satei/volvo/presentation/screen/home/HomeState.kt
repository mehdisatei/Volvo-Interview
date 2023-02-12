package mehdi.satei.volvo.presentation.screen.home

import mehdi.satei.volvo.presentation.screen.home.model.WeatherSummary

data class HomeState(
    val weatherList: List<WeatherSummary> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)