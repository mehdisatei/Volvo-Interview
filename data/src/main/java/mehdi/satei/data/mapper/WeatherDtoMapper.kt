package mehdi.satei.data.mapper

import mehdi.satei.data.api.response.WeatherDto
import mehdi.satei.data.mapper.base.DtoMapper
import mehdi.satei.domain.model.WeatherModel
import javax.inject.Inject

class WeatherDtoMapper @Inject constructor() : DtoMapper<WeatherDto, WeatherModel> {

    override fun mapToModel(dto: WeatherDto): WeatherModel = WeatherModel(
        cityName = dto.name,
        temp = dto.main.temperature.toInt(),
        minTemp = dto.main.temperatureMin.toInt(),
        maxTemp = dto.main.temperatureMax.toInt(),
        condition = dto.weatherData.first().description,
        icon = dto.weatherData.first().icon,
        humidity = dto.main.humidity,
        windSpeed = dto.wind.speed.toInt(),
        feelsLike = dto.main.feelsLike.toInt(),
    )
}