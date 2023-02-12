package mehdi.satei.data.repository.dataSource

import kotlinx.coroutines.flow.Flow
import mehdi.satei.data.db.WeatherEntity

interface WeatherLocalDataSource {

    suspend fun addWeatherToDB(weatherEntity: WeatherEntity)

    fun getWeatherFromDB(id: String): Flow<WeatherEntity>

}