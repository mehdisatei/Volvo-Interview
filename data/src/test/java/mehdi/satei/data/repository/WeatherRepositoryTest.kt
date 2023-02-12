package mehdi.satei.data.repository

import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import mehdi.satei.data.mapper.WeatherEntityMapper
import mehdi.satei.data.mapper.WeatherModelMapper
import mehdi.satei.data.repository.dataSource.WeatherLocalDataSource
import mehdi.satei.data.repository.dataSource.WeatherRemoteDataSource
import mehdi.satei.domain.model.WeatherModel
import mehdi.satei.domain.util.Resource
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WeatherRepositoryTest {

    private lateinit var weatherRepository: WeatherRepositoryImpl
    private lateinit var weatherRemoteDataSource: WeatherRemoteDataSource
    private lateinit var weatherLocalDataSource: WeatherLocalDataSource
    private val weatherEntityMapper = WeatherEntityMapper()
    private val weatherModelMapper = WeatherModelMapper()

    @BeforeEach
    fun setup() {
        weatherRemoteDataSource = mockk()
        weatherLocalDataSource = mockk()

        weatherRepository = WeatherRepositoryImpl(
            weatherRemoteDataSource = weatherRemoteDataSource,
            weatherLocalDataSource = weatherLocalDataSource,
            weatherEntityMapper = weatherEntityMapper,
            weatherModelMapper = weatherModelMapper
        )
    }

    @AfterEach
    fun clean() {
        unmockkAll()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getWeatherSuccessTest() {
        val weatherModel = WeatherModel(
            cityName = "cityName",
            temp = 1,
            maxTemp = 2,
            minTemp = 3,
            feelsLike = 4,
            humidity = 5,
            windSpeed = 6,
            condition = "condition",
            icon = "icon"
        )
        runTest {
            coEvery { weatherRemoteDataSource.getWeather(any()) } returns Resource.Success(
                weatherModel
            )
            coEvery { weatherLocalDataSource.addWeatherToDB(any()) } just Runs
            val resource = weatherRepository.getWeather("city")
            assert(resource is Resource.Success)
            assert((resource as Resource.Success).data == weatherModel)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getWeatherErrorTest() {
        val error = "errorMessage"
        runTest {
            coEvery { weatherRemoteDataSource.getWeather(any()) } returns Resource.Error(error)
            val resource = weatherRepository.getWeather("city")
            assert(resource is Resource.Error)
            assert((resource as Resource.Error).message == error)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getWeatherSuccessNullBodyTest() {
        runTest {
            coEvery { weatherRemoteDataSource.getWeather(any()) } returns Resource.Success(null)
            val resource = weatherRepository.getWeather("city")
            assert(resource is Resource.Success)
            assert((resource as Resource.Success).data == null)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAllWeatherSuccessTest() {
        val weatherModel1 = WeatherModel(
            cityName = "cityName",
            temp = 1,
            maxTemp = 2,
            minTemp = 3,
            feelsLike = 4,
            humidity = 5,
            windSpeed = 6,
            condition = "condition",
            icon = "icon"
        )
        val weatherModel2 = WeatherModel(
            cityName = "cityName2",
            temp = 12,
            maxTemp = 22,
            minTemp = 32,
            feelsLike = 42,
            humidity = 52,
            windSpeed = 62,
            condition = "condition2",
            icon = "icon2"
        )
        runTest {
            coEvery { weatherRemoteDataSource.getAllWeather(any()) } returns Resource.Success(
                listOf(weatherModel1, weatherModel2)
            )
            coEvery { weatherLocalDataSource.addWeatherToDB(any()) } just Runs
            val resource = weatherRepository.getAllWeather(listOf("cityName", "cityName2"))
            assert(resource is Resource.Success)
            assert((resource as Resource.Success).data == listOf(weatherModel1, weatherModel2))
        }
    }

}