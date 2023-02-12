package mehdi.satei.data.mapper

import mehdi.satei.data.db.WeatherEntity
import mehdi.satei.data.mapper.base.ModelMapper
import mehdi.satei.domain.model.WeatherModel
import javax.inject.Inject

class WeatherModelMapper @Inject constructor(): ModelMapper<WeatherModel, WeatherEntity> {

    override fun mapToEntity(model: WeatherModel): WeatherEntity = WeatherEntity(
        cityName = model.cityName,
        temp = model.temp,
        maxTemp = model.maxTemp,
        minTemp = model.minTemp,
        feelsLike = model.feelsLike,
        humidity = model.humidity,
        windSpeed = model.windSpeed,
        condition = model.condition,
        icon = model.icon
    )
}