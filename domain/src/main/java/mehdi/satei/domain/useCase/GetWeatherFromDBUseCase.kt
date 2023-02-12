package mehdi.satei.domain.useCase

import mehdi.satei.domain.repository.WeatherRepository

class GetWeatherFromDBUseCase(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(id: String) = weatherRepository.getWeatherFromDB(id)
}