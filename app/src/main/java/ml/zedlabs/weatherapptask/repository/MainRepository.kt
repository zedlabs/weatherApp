package ml.zedlabs.weatherapptask.repository

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ml.zedlabs.weatherapptask.di.AppModule
import ml.zedlabs.weatherapptask.repository.localDb.WeatherDao
import ml.zedlabs.weatherapptask.repository.models.CityWeatherData
import ml.zedlabs.weatherapptask.repository.models.WeatherResponse
import ml.zedlabs.weatherapptask.repository.remote.JsonApi
import ml.zedlabs.weatherapptask.util.toCWD
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val jsonApi: JsonApi,
    private val dao: WeatherDao
) {

    val cityWeatherList: Flow<List<CityWeatherData>> = dao.getAll()

    suspend fun getWeatherData(city: String): WeatherResponse {
        return jsonApi.getWeatherData(city, AppModule.apiKey).body()!!
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun insertCityWeatherData(weatherResponse: WeatherResponse) {
        withContext(Dispatchers.IO) {
            if (dao.getDetailsById(weatherResponse.id).isEmpty())
                dao.insertCityWeather(weatherResponse.toCWD())
        }
    }

    suspend fun deleteCityData(data: CityWeatherData) = dao.deleteItem(data)


}