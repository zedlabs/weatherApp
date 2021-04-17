package ml.zedlabs.weatherapptask.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ml.zedlabs.weatherapptask.repository.localDb.WeatherDao
import ml.zedlabs.weatherapptask.repository.localDb.WeatherDatabase
import ml.zedlabs.weatherapptask.repository.remote.JsonApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()


    @Provides
    @Singleton
    fun providesRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesJsonApi(retrofit: Retrofit): JsonApi = retrofit.create(JsonApi::class.java)

    companion object {
        val baseUrl = "https://api.openweathermap.org"
        val apiKey = "615f14c55db3b977157e1ea2887870d4"
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext application: Context): WeatherDatabase {
        return Room
            .databaseBuilder(application, WeatherDatabase::class.java, "weather-database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(weatherdatabase: WeatherDatabase) : WeatherDao {
        return weatherdatabase.getWeatherDao()
    }
}