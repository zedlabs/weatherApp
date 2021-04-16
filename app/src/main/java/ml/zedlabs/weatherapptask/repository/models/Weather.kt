package ml.zedlabs.weatherapptask.repository.models

import com.squareup.moshi.Json

data class Weather (

    @field:Json(name ="id") var id : Int,
    @field:Json(name ="main") var main : String,
    @field:Json(name ="description") var description : String,
    @field:Json(name ="icon") var icon : String

)