package mehdi.satei.data.api.response

import com.google.gson.annotations.SerializedName
import mehdi.satei.data.mapper.base.Dto
import java.io.Serializable

data class WeatherDto(
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: CloudsDto,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("coord")
    val coord: CoordDto,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: MainDto,
    @SerializedName("name")
    val name: String,
    @SerializedName("rain")
    val rain: RainDto,
    @SerializedName("sys")
    val sys: SysDto,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weatherData: List<WeatherDataDto>,
    @SerializedName("wind")
    val wind: WindDto
): Serializable, Dto

data class CloudsDto(
    @SerializedName("all")
    val all: Int
): Serializable, Dto

data class CoordDto(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double
): Serializable, Dto

data class MainDto(
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("grnd_level")
    val groundLevel: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("temp_max")
    val temperatureMax: Double,
    @SerializedName("temp_min")
    val temperatureMin: Double
): Serializable, Dto

data class RainDto(
    @SerializedName("1h")
    val oneHour: Double
): Serializable, Dto

data class SysDto(
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @SerializedName("type")
    val type: Int
): Serializable, Dto

data class WeatherDataDto(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
): Serializable, Dto

data class WindDto(
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("gust")
    val gust: Double,
    @SerializedName("speed")
    val speed: Double
): Serializable, Dto