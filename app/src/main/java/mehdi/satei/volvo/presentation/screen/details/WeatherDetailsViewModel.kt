package mehdi.satei.volvo.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mehdi.satei.domain.useCase.GetWeatherFromDBUseCase
import mehdi.satei.volvo.presentation.screen.details.mapper.WeatherDetailsMapper
import mehdi.satei.volvo.presentation.screen.details.model.WeatherDetails
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getWeatherFromDBUseCase: GetWeatherFromDBUseCase,
    private val weatherDetailsMapper: WeatherDetailsMapper
) : ViewModel() {

    private val _weatherDetails: MutableStateFlow<WeatherDetails?> = MutableStateFlow(null)
    val weatherDetails: StateFlow<WeatherDetails?> = _weatherDetails

    fun getWeatherDetails(id: String) {
        viewModelScope.launch {
            getWeatherFromDBUseCase.invoke(id = id).collect {
                _weatherDetails.value =
                    weatherDetailsMapper.mapToUIModel(model = it)
            }
        }
    }
}