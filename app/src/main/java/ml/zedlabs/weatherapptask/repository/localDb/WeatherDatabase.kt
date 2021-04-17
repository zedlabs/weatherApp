package ml.zedlabs.weatherapptask.repository.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import ml.zedlabs.weatherapptask.repository.models.CityWeatherData

@Database(entities = [CityWeatherData::class], version = 2)
abstract class WeatherDatabase: RoomDatabase(){
    abstract fun getWeatherDao() : WeatherDao
}