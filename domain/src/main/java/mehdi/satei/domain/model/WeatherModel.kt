package mehdi.satei.domain.model

data class WeatherModel(
    val cityName: String,
    val temp: Int,
    val maxTemp: Int,
    val minTemp: Int,
    val feelsLike: Int,
    val humidity: Int,
    val windSpeed: Int,
    val condition: String,
    val icon: String
): Model