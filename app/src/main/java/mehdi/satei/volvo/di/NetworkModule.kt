package mehdi.satei.volvo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mehdi.satei.data.api.WeatherApi
import mehdi.satei.volvo.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        clientInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(clientInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppIdQueryParamInterceptor(): Interceptor {
        return Interceptor() {
            var request = it.request()
            val url = request.url.newBuilder()
                .addQueryParameter(
                    name = "appid",
                    value = BuildConfig.APP_ID
                ).build()
            request = request.newBuilder().url(url).build()
            it.proceed(request)
        }
    }

}