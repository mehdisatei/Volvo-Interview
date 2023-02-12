package mehdi.satei.volvo.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mehdi.satei.data.db.WeatherDB
import mehdi.satei.data.db.WeatherDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(app: Application): WeatherDB =
        Room.databaseBuilder(app, WeatherDB::class.java, "weather_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideWeatherDao(weatherDB: WeatherDB): WeatherDao = weatherDB.weatherDao()
}