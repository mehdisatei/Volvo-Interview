package mehdi.satei.data.mapper

import mehdi.satei.data.db.WeatherEntity
import mehdi.satei.domain.model.WeatherModel
import org.junit.jupiter.api.Test

class WeatherModelMapperTest {

    private val weatherModel = WeatherModel(
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
    fun weatherModelMapper() {
        val modelMapper = WeatherModelMapper()
        val weatherEntity = modelMapper.mapToEntity(weatherModel)
        val expectedWeatherEntity = WeatherEntity(
            cityName = weatherModel.cityName,
            temp = weatherModel.temp,
            minTemp = weatherModel.minTemp,
            maxTemp = weatherModel.maxTemp,
            condition = weatherModel.condition,
            icon = weatherModel.icon,
            humidity = weatherModel.humidity,
            windSpeed = weatherModel.windSpeed,
            feelsLike = weatherModel.feelsLike
        )

        assert(weatherEntity == expectedWeatherEntity)
    }
}