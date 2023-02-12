package mehdi.satei.domain.useCase

import mehdi.satei.domain.repository.WeatherRepository

class GetAllWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(cities: List<String>) = weatherRepository.getAllWeather(cities)
}