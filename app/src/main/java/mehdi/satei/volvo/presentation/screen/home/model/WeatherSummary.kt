package mehdi.satei.volvo.presentation.screen.home.model

import mehdi.satei.volvo.presentation.mapper.UiModel

data class WeatherSummary(
    val cityName: String,
    val temp: Int,
    val maxTemp: Int,
    val minTemp: Int,
    val condition: String,
    val icon: String
): UiModel