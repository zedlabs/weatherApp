package ml.zedlabs.weatherapptask.ui

import androidx.lifecycle.*
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

    val _data  = MutableLiveData<List<CityWeatherData>>()
    val data : LiveData<List<CityWeatherData>> = _data

    fun getCityWeatherData(city: String) {
        viewModelScope.launch {
            val dt = repository.getWeatherData(city = city)
            repository.insertCityWeatherData(dt)
        }
    }

    fun getFavCityData(){
        viewModelScope.launch {
            _data.value = repository.getFavCityData()
        }

    }

}