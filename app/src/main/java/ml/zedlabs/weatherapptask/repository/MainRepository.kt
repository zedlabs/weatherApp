package ml.zedlabs.weatherapptask.repository

import android.util.Log
import ml.zedlabs.weatherapptask.di.AppModule
import ml.zedlabs.weatherapptask.repository.localDb.WeatherDao
import ml.zedlabs.weatherapptask.repository.models.WeatherResponse
import ml.zedlabs.weatherapptask.repository.remote.JsonApi
import ml.zedlabs.weatherapptask.util.toCWD
import javax.inject.Inject

class MainRepository @Inject constructor(
    val jsonApi: JsonApi,
    val dao: WeatherDao
) {
    suspend fun getWeatherData(city: String) : WeatherResponse{
        return jsonApi.getWeatherData(city, AppModule.apiKey).body()!!
    }

    suspend fun insertCityWeatherData(weatherResponse: WeatherResponse){
        Log.e("repo", "1->insertCityWeatherData: ${weatherResponse.name}")
        dao.insertCityWeather(weatherResponse.toCWD())
    }

    suspend fun getFavCityData() = dao.getAll()

}