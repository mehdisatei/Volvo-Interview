package mehdi.satei.volvo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mehdi.satei.domain.repository.WeatherRepository
import mehdi.satei.domain.useCase.GetAllWeatherUseCase
import mehdi.satei.domain.useCase.GetWeatherFromDBUseCase
import mehdi.satei.domain.useCase.GetWeatherUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetWeatherUseCase(weatherRepository: WeatherRepository) =
        GetWeatherUseCase(weatherRepository = weatherRepository)

    @Provides
    fun provideGetAllWeatherUseCase(weatherRepository: WeatherRepository) =
        GetAllWeatherUseCase(weatherRepository = weatherRepository)

    @Provides
    fun provideGetWeatherFromFBUseCase(weatherRepository: WeatherRepository) =
        GetWeatherFromDBUseCase(weatherRepository = weatherRepository)
}