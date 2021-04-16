package ml.zedlabs.weatherapptask.repository.models

import com.squareup.moshi.Json

data class Main (

    @field:Json(name = "temp") var temp : Double,
    @field:Json(name = "feels_like") var feelsLike : Double,
    @field:Json(name = "temp_min") var tempMin : Double,
    @field:Json(name = "temp_max") var tempMax : Double,
    @field:Json(name = "pressure") var pressure : Int,
    @field:Json(name = "humidity") var humidity : Int

)