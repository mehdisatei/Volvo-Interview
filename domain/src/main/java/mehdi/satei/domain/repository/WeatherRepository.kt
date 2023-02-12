package mehdi.satei.domain.repository

import kotlinx.coroutines.flow.Flow
import mehdi.satei.domain.model.WeatherModel
import mehdi.satei.domain.util.Resource

interface WeatherRepository {

    suspend fun getWeather(city: String): Resource<WeatherModel>
    fun getWeatherFromDB(id: String): Flow<WeatherModel>
    suspend fun getAllWeather(cities: List<String>): Resource<List<WeatherModel>>
}