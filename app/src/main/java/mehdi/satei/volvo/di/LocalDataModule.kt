package mehdi.satei.volvo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mehdi.satei.data.db.WeatherDao
import mehdi.satei.data.repository.dataSource.WeatherLocalDataSource
import mehdi.satei.data.repository.dataSourceImpl.WeatherLocalDataSourceImpl


@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideLocalDataSource(weatherDao: WeatherDao): WeatherLocalDataSource =
        WeatherLocalDataSourceImpl(weatherDao = weatherDao)
}