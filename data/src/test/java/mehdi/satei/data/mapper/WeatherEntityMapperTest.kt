package mehdi.satei.data.mapper

import mehdi.satei.data.db.WeatherEntity
import mehdi.satei.domain.model.WeatherModel
import org.junit.jupiter.api.Test

class WeatherEntityMapperTest {

    private val weatherEntity = WeatherEntity(
        cityName = "cityName",
        temp = 1,
        maxTemp = 2,
        minTemp = 3,
        feelsLike = 4,
        humidity = 5,
        windSpeed = 6,
        condition = "condition",
        icon = "icon"
    )

    @Test
    fun weatherEntityMapperTest() {
        val weatherEntityMapper = WeatherEntityMapper()
        val weatherModel = weatherEntityMapper.mapToModel(weatherEntity)
        val expectedWeatherModel = WeatherModel(
            cityName = weatherEntity.cityName,
            temp = weatherEntity.temp,
            minTemp = weatherEntity.minTemp,
            maxTemp = weatherEntity.maxTemp,
            condition = weatherEntity.condition,
            icon = weatherEntity.icon,
            humidity = weatherEntity.humidity,
            windSpeed = weatherEntity.windSpeed,
            feelsLike = weatherEntity.feelsLike
        )

        assert(weatherModel == expectedWeatherModel)
    }
}