package mehdi.satei.volvo.presentation.screen.details.mapper

import mehdi.satei.domain.model.WeatherModel
import mehdi.satei.volvo.R
import mehdi.satei.volvo.presentation.mapper.ModelMapper
import mehdi.satei.volvo.presentation.screen.details.model.WeatherDataDisplayData
import mehdi.satei.volvo.presentation.screen.details.model.WeatherDetails
import javax.inject.Inject

class WeatherDetailsMapper @Inject constructor() : ModelMapper<WeatherModel, WeatherDetails> {
    
    override fun mapToUIModel(
        model: WeatherModel,
    ): WeatherDetails {
        return WeatherDetails(
            cityName = model.cityName,
            temp = model.temp,
            condition = model.condition,
            icon = model.icon,
            weatherDataDisplayList = listOf(
                WeatherDataDisplayData(
                    title = "Max Temp",
                    value = model.maxTemp,
                    icon = R.drawable.ic_temp,
                    unit = "°C"
                ),
                WeatherDataDisplayData(
                    title = "Min Temp",
                    value = model.minTemp,
                    icon = R.drawable.ic_temp,
                    unit = "°C"
                ),
                WeatherDataDisplayData(
                    title = "Feels Like",
                    value = model.feelsLike,
                    icon = R.drawable.ic_temp,
                    unit = "°C"
                ),
                WeatherDataDisplayData(
                    title = "Humidity",
                    value = model.humidity,
                    icon = R.drawable.ic_humidity,
                    unit = "%"
                ),
                WeatherDataDisplayData(
                    title = "Wind Speed",
                    value = model.windSpeed,
                    icon = R.drawable.ic_wind,
                    unit = "%"
                ),
            ),
        )
    }
}