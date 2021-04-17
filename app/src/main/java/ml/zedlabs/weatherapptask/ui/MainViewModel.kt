package ml.zedlabs.weatherapptask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.zedlabs.weatherapptask.repository.MainRepository
import ml.zedlabs.weatherapptask.repository.models.CityWeatherData
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

}