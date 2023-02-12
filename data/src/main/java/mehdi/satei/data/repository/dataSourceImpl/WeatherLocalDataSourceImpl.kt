package mehdi.satei.data.repository.dataSourceImpl

import kotlinx.coroutines.flow.Flow
import mehdi.satei.data.db.WeatherDao
import mehdi.satei.data.db.WeatherEntity
import mehdi.satei.data.repository.dataSource.WeatherLocalDataSource

class WeatherLocalDataSourceImpl(
    private val weatherDao: WeatherDao
): WeatherLocalDataSource {

    override suspend fun addWeatherToDB(weatherEntity: WeatherEntity) {
        weatherDao.addWeather(weatherEntity)
    }
    override fun getWeatherFromDB(id: String): Flow<WeatherEntity> =
        weatherDao.getWeather(id)
}