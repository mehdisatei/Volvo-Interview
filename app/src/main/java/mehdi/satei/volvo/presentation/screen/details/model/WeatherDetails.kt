package mehdi.satei.volvo.presentation.screen.details.model

import androidx.annotation.DrawableRes
import mehdi.satei.volvo.presentation.mapper.UiModel

data class WeatherDetails(
    val cityName: String,
    val temp: Int,
    val condition: String,
    val icon: String,
    val weatherDataDisplayList: List<WeatherDataDisplayData> = listOf()
): UiModel

data class WeatherDataDisplayData(
    val title: String,
    val value: Int,
    val unit: String,
    @DrawableRes val icon: Int,
): UiModel
