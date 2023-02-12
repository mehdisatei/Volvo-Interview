package mehdi.satei.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = false)
    val cityName: String,
    val temp: Int,
    val maxTemp: Int,
    val minTemp: Int,
    val feelsLike: Int,
    val humidity: Int,
    val windSpeed: Int,
    val condition: String,
    val icon: String
): mehdi.satei.data.mapper.base.Entity