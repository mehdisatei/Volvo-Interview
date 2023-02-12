package mehdi.satei.volvo.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mehdi.satei.domain.useCase.GetAllWeatherUseCase
import mehdi.satei.volvo.presentation.screen.home.mapper.WeatherSummaryMapper
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllWeatherUseCase: GetAllWeatherUseCase,
    private val weatherSummaryMapper: WeatherSummaryMapper
) : ViewModel() {


    private val stateFlow = MutableStateFlow(HomeState())
    fun getState() = stateFlow.asStateFlow()

    fun loadWeather() {
        viewModelScope.launch {
            stateFlow.value = stateFlow.value.copy(
                isLoading = true,
                error = null
            )

            val weatherListResult = getAllWeatherUseCase.invoke(
                listOf(
                    "stockholm",
                    "gothenburg",
                    "london",
                    "berlin",
                    "mountainview",
                    "new york"
                )
            )

            weatherListResult.data?.let { weatherList ->
                stateFlow.value = stateFlow.value.copy(
                    weatherList = weatherList.map {
                        weatherSummaryMapper.mapToUIModel(model = it)
                    },
                    isLoading = false,
                    error = null
                )
            } ?: run {
                stateFlow.value = stateFlow.value.copy(
                    isLoading = false,
                    error = weatherListResult.message ?: "Unable to fetch data"
                )
            }

        }
    }
}