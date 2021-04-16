package ml.zedlabs.weatherapptask.repository.remote

import ml.zedlabs.weatherapptask.repository.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonApi {

    @GET("/data/2.5/weather/")
    suspend fun getWeatherData(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
    ): Response<WeatherResponse>

}