package mehdi.satei.data.mapper

import mehdi.satei.data.api.response.*
import mehdi.satei.domain.model.WeatherModel
import org.junit.jupiter.api.Test


class WeatherDtoMapperTest {

    private val weatherDto = WeatherDto(
        base = "base",
        clouds = CloudsDto(
            all = 1
        ),
        cod = 2,
        coord = CoordDto(
            latitude = 3.0,
            longitude = 4.0
        ),
        dt = 5,
        id = 6,
        main = MainDto(
            feelsLike = 7.0,
            groundLevel = 8,
            humidity = 9,
            pressure = 10,
            seaLevel = 11,
            temperature = 12.0,
            temperatureMax = 13.0,
            temperatureMin = 14.0
        ),
        name = "name",
        rain = RainDto(
            oneHour = 15.0
        ),
        sys = SysDto(
            country = "country",
            id = 16,
            sunrise = 17,
            sunset = 18,
            type = 19
        ),
        timezone = 1,
        visibility = 1,
        weatherData = listOf(
            WeatherDataDto(
                description = "description",
                icon = "icon",
                id = 20,
                main = "main"
            )
        ),
        wind = WindDto(
            deg = 21,
            gust = 22.0,
            speed = 23.0
        )
    )

    @Test
    fun weatherDtoMapperTest() {
        val weatherDtoMapper = WeatherDtoMapper()
        val weatherModel = weatherDtoMapper.mapToModel(weatherDto)
        val expectedWeatherModel = WeatherModel(
            cityName = weatherDto.name,
            temp = weatherDto.main.temperature.toInt(),
            minTemp = weatherDto.main.temperatureMin.toInt(),
            maxTemp = weatherDto.main.temperatureMax.toInt(),
            condition = weatherDto.weatherData.first().description,
            icon = weatherDto.weatherData.first().icon,
            humidity = weatherDto.main.humidity,
            windSpeed = weatherDto.wind.speed.toInt(),
            feelsLike = weatherDto.main.feelsLike.toInt(),
        )

        assert(weatherModel == expectedWeatherModel)
    }
}