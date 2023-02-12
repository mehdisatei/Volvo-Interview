package mehdi.satei.data.api

import mehdi.satei.data.api.response.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q", encoded = true) city: String,
        @Query("units") units: String = "metric"
    ): Response<WeatherDto>
}