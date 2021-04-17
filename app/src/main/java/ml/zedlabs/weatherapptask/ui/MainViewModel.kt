package ml.zedlabs.weatherapptask.ui

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ml.zedlabs.weatherapptask.repository.MainRepository
import ml.zedlabs.weatherapptask.repository.models.CityWeatherData
import ml.zedlabs.weatherapptask.repository.models.WeatherResponse
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: MainRepository
) : ViewModel() {

    var _data = repository.cityWeatherList.asLiveData()
    val data: LiveData<List<CityWeatherData>> = _data

    fun getCityWeatherData(city: String) {
        viewModelScope.launch {
            val dt = repository.getWeatherData(city = city)
            repository.insertCityWeatherData(dt)
        }
    }

    init {
        viewModelScope.launch {
            delay(2000)
            Log.e("viewModel", "1-> ${data.value?.size}")
        }


    }
//    fun getFavCityData(){
//
//
//    }

}