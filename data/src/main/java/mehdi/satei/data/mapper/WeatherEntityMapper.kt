package mehdi.satei.data.mapper

import mehdi.satei.data.db.WeatherEntity
import mehdi.satei.data.mapper.base.EntityMapper
import mehdi.satei.domain.model.WeatherModel
import javax.inject.Inject

class WeatherEntityMapper @Inject constructor(): EntityMapper<WeatherEntity, WeatherModel> {

    override fun mapToModel(entity: WeatherEntity): WeatherModel = WeatherModel(
        cityName = entity.cityName,
        temp = entity.temp,
        maxTemp = entity.maxTemp,
        minTemp = entity.minTemp,
        feelsLike = entity.feelsLike,
        humidity = entity.humidity,
        windSpeed = entity.windSpeed,
        condition = entity.condition,
        icon = entity.icon
    )
}