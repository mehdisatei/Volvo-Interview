package mehdi.satei.data.repository.dataSource

import mehdi.satei.domain.model.WeatherModel
import mehdi.satei.domain.util.Resource

interface WeatherRemoteDataSource {
    suspend fun getWeather(city: String): Resource<WeatherModel>
    suspend fun getAllWeather(cities: List<String>): Resource<List<WeatherModel>>
}