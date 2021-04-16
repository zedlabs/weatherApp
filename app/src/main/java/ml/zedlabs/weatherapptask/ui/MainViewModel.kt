package ml.zedlabs.weatherapptask.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ml.zedlabs.weatherapptask.repository.MainRepository
import ml.zedlabs.weatherapptask.repository.models.WeatherResponse
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: MainRepository
) : ViewModel() {
    suspend fun getCityWeatherData(city: String): WeatherResponse =
        repository.getWeatherData(city = city)
}