package ml.zedlabs.weatherapptask.util

import android.os.Build
import androidx.annotation.RequiresApi
import ml.zedlabs.weatherapptask.repository.models.CityWeatherData
import ml.zedlabs.weatherapptask.repository.models.WeatherResponse


@RequiresApi(Build.VERSION_CODES.O)
fun WeatherResponse.toCWD(): CityWeatherData =
    CityWeatherData(
        id = this.id,
        city = this.name,
        weatherDescription = this.weather[0].description,
        currentTemp = this.main.temp,
        tempMin = this.main.tempMin,
        tempMax = this.main.tempMax,
        humidity = this.main.humidity,
        pressure = this.main.pressure,
        time = java.time.format.DateTimeFormatter.ISO_INSTANT
            .format(java.time.Instant.ofEpochSecond(this.dt))
            .dropLast(10)
    )
