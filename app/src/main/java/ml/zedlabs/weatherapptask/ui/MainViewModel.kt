package ml.zedlabs.weatherapptask.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.zedlabs.weatherapptask.repository.MainRepository
import ml.zedlabs.weatherapptask.repository.models.CityWeatherData
import ml.zedlabs.weatherapptask.repository.models.WeatherResponse
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: MainRepository
) : ViewModel() {

    private var _data = repository.cityWeatherList.asLiveData()
    val data: LiveData<List<CityWeatherData>> = _data

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCityWeatherData(city: String) {
        viewModelScope.launch {
            val dt = try {
                repository.getWeatherData(city = city)
            }catch (e: Exception){
                print(e.message)
            }
            if(dt is WeatherResponse){
                repository.insertCityWeatherData(dt)
            }
        }
    }

    suspend fun getCityWeatherDataByName(city: String) = repository.getCityWeatherById(city)


    fun deleteCityData(data: CityWeatherData){
        viewModelScope.launch {
            repository.deleteCityData(data)
        }
    }
}