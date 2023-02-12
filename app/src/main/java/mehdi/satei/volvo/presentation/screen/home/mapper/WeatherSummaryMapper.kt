package mehdi.satei.volvo.presentation.screen.home.mapper

import mehdi.satei.domain.model.WeatherModel
import mehdi.satei.volvo.presentation.mapper.ModelMapper
import mehdi.satei.volvo.presentation.screen.home.model.WeatherSummary
import mehdi.satei.volvo.presentation.util.buildIconUrl
import javax.inject.Inject

class WeatherSummaryMapper @Inject constructor() : ModelMapper<WeatherModel, WeatherSummary> {

    override fun mapToUIModel(model: WeatherModel, ): WeatherSummary {
        return WeatherSummary(
            cityName = model.cityName,
            temp = model.temp,
            maxTemp = model.maxTemp,
            minTemp = model.minTemp,
            condition = model.condition,
            icon = model.icon
        )
    }
}
