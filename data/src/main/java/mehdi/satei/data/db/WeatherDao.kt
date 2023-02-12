package mehdi.satei.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWeather(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weather WHERE cityName == :id")
    fun getWeather(id: String): Flow<WeatherEntity>

    @Query("DELETE FROM weather")
    suspend fun deleteAllWeather()
}