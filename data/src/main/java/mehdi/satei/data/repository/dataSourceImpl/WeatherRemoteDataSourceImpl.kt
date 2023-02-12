package mehdi.satei.data.repository.dataSourceImpl

import kotlinx.coroutines.*
import mehdi.satei.data.api.WeatherApi
import mehdi.satei.data.base.AbstractApiService
import mehdi.satei.data.mapper.WeatherDtoMapper
import mehdi.satei.data.repository.dataSource.WeatherRemoteDataSource
import mehdi.satei.domain.model.WeatherModel
import mehdi.satei.domain.util.Resource
import mehdi.satei.domain.util.map
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherDtoMapper: WeatherDtoMapper
) : AbstractApiService<WeatherApi>(
    WeatherApi::class.java,
), WeatherRemoteDataSource {

    override suspend fun getWeather(city: String): Resource<WeatherModel> {
        val weather = execution {
            apiService.getWeather(
                city,
            )
        }.map {
            weatherDtoMapper.mapToModel(it)
        }
        return weather
    }

    override suspend fun getAllWeather(cities: List<String>): Resource<List<WeatherModel>> =
        coroutineScope {
            val weatherModelList: List<WeatherModel> = cities.map {
                async { getWeather(it) }
            }.awaitAll().filter {
                it is Resource.Success
            }.mapNotNull {
                it.data
            }

            if (weatherModelList.isNotEmpty()) {
                Resource.Success(weatherModelList)
            } else {
                Resource.Error("unable to fetch any weather")
            }
        }
}