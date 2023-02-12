package mehdi.satei.domain.useCase

import mehdi.satei.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(city: String) = weatherRepository.getWeather(city)
}