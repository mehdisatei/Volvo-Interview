package mehdi.satei.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WeatherEntity::class],
    version = 4,
    exportSchema = false
)
abstract class WeatherDB: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}