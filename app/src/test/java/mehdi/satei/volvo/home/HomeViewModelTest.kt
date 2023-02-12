package mehdi.satei.volvo.home

import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import mehdi.satei.domain.model.WeatherModel
import mehdi.satei.domain.useCase.GetAllWeatherUseCase
import mehdi.satei.domain.util.Resource
import mehdi.satei.volvo.presentation.screen.home.HomeState
import mehdi.satei.volvo.presentation.screen.home.HomeViewModel
import mehdi.satei.volvo.presentation.screen.home.mapper.WeatherSummaryMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var getAllWeatherUseCase: GetAllWeatherUseCase
    private val weatherSummaryMapper = WeatherSummaryMapper()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        getAllWeatherUseCase = mockk()
        homeViewModel = HomeViewModel(
            getAllWeatherUseCase = getAllWeatherUseCase,
            weatherSummaryMapper = weatherSummaryMapper
        )
    }

    @AfterEach
    fun clean() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun testLoadWeatherSuccess() {
        runTest {
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

            coEvery { getAllWeatherUseCase.invoke(any()) } returns Resource.Success(
                listOf(
                    weatherModel1, weatherModel2
                )
            )


            homeViewModel.loadWeather()
            val state = homeViewModel.getState().value
            val expectedState = HomeState(
                weatherList = listOf(
                    weatherSummaryMapper.mapToUIModel(weatherModel1),
                    weatherSummaryMapper.mapToUIModel(weatherModel2)
                ),
                isLoading = false,
                error = null
            )
            assert(state == expectedState)
        }
    }

    @Test
    fun testLoadWeatherError() {
        val error = "errorMessage"
        runTest {
            coEvery { getAllWeatherUseCase.invoke(any()) } returns Resource.Error(error)
            homeViewModel.loadWeather()
            val state = homeViewModel.getState().value
            val expectedState = HomeState(
                weatherList = listOf(),
                isLoading = false,
                error = error
            )
            assert(state == expectedState)
        }
    }
}