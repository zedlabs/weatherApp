package ml.zedlabs.weatherapptask.repository.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable