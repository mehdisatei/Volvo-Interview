package mehdi.satei.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mehdi.satei.data.db.WeatherEntity
import mehdi.satei.data.mapper.WeatherEntityMapper
import mehdi.satei.data.mapper.WeatherModelMapper
import mehdi.satei.data.repository.dataSource.WeatherLocalDataSource
import mehdi.satei.data.repository.dataSource.WeatherRemoteDataSource
import mehdi.satei.domain.model.WeatherModel
import mehdi.satei.domain.repository.WeatherRepository
import mehdi.satei.domain.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherEntityMapper: WeatherEntityMapper,
    private val weatherModelMapper: WeatherModelMapper
) : WeatherRepository {

    override suspend fun getWeather(city: String): Resource<WeatherModel> {
        // get weather from remote
        val weatherResource = weatherRemoteDataSource.getWeather(city)
        // add weather to DB if successful
        weatherResource.data?.let { addWeatherEntityToDB(weatherModelMapper.mapToEntity(it)) }
        // return resource
        return weatherResource
    }

    override fun getWeatherFromDB(id: String): Flow<WeatherModel> =
        weatherLocalDataSource.getWeatherFromDB(id).map {
            weatherEntityMapper.mapToModel(entity = it)
        }

    override suspend fun getAllWeather(cities: List<String>): Resource<List<WeatherModel>> {
        return weatherRemoteDataSource.getAllWeather(cities).apply {
            data?.forEach {
                addWeatherEntityToDB(weatherModelMapper.mapToEntity(it))
            }
        }
    }

    private suspend fun addWeatherEntityToDB(weatherEntity: WeatherEntity) {
        weatherLocalDataSource.addWeatherToDB(weatherEntity)
    }

}