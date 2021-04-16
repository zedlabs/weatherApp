package ml.zedlabs.weatherapptask.repository.models

import com.squareup.moshi.Json

data class WeatherResponse (

    //@field:Json(name = "coord") var coord : Coord,
    @field:Json(name = "weather") var weather : List<Weather>,
    @field:Json(name = "base") var base : String,
    @field:Json(name = "main") var main : Main,
    @field:Json(name = "visibility") var visibility : Int,
    //@field:Json(name = "clouds") var clouds : Clouds,
    @field:Json(name = "dt") var dt : Int,
    //@field:Json(name = "sys") var sys : Sys,
    @field:Json(name = "timezone") var timezone : Int,
    @field:Json(name = "id") var id : Int,
    @field:Json(name = "name") var name : String,
    @field:Json(name = "cod") var cod : Int
)