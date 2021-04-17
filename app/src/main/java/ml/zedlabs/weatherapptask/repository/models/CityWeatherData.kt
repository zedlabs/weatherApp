package ml.zedlabs.weatherapptask.repository.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class CityWeatherData (
    @PrimaryKey val id: Int,
    val city: String,
    val weatherDescription: String,
    val currentTemp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val humidity: Int,
    val pressure: Int,
    val time: String,
)