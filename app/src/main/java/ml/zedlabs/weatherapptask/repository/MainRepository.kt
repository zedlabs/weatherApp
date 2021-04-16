package ml.zedlabs.weatherapptask.repository

import ml.zedlabs.weatherapptask.di.AppModule
import ml.zedlabs.weatherapptask.repository.models.WeatherResponse
import ml.zedlabs.weatherapptask.repository.remote.JsonApi
import javax.inject.Inject

class MainRepository @Inject constructor(
    val jsonApi: JsonApi
) {
    suspend fun getWeatherData(city: String) : WeatherResponse{
        return jsonApi.getWeatherData(city, AppModule.apiKey).body()!!
    }
}