package ml.zedlabs.weatherapptask.util

import ml.zedlabs.weatherapptask.repository.models.CityWeatherData
import ml.zedlabs.weatherapptask.repository.models.WeatherResponse


fun WeatherResponse.toCWD(): CityWeatherData =
    CityWeatherData(
        id = this.id,
        city = this.name,
        weatherDescription = this.weather[0].description,
        tempMin = this.main.tempMin,
        tempMax = this.main.tempMax,
        humidity = this.main.humidity,
        pressure = this.main.pressure
    )
