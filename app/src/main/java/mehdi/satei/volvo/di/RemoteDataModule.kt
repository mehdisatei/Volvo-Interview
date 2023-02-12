package mehdi.satei.volvo.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mehdi.satei.data.api.WeatherApi
import mehdi.satei.data.db.WeatherDB
import mehdi.satei.data.repository.dataSource.WeatherRemoteDataSource
import mehdi.satei.data.repository.dataSourceImpl.WeatherRemoteDataSourceImpl
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataModule {
    @Binds
    abstract fun provideWeatherRemoteDataSource(
        weatherRemoteDataSourceImpl: WeatherRemoteDataSourceImpl
    ): WeatherRemoteDataSource
}