package ml.zedlabs.weatherapptask.repository.localDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ml.zedlabs.weatherapptask.repository.models.CityWeatherData

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getAll(): Flow<List<CityWeatherData>>

    @Insert
    suspend fun insertCityWeather(data: CityWeatherData)

    @Query("SELECT * FROM weather WHERE id == :current")
    fun getDetailsById(current: Int): List<CityWeatherData>

    @Query("SELECT * FROM weather WHERE city == :current")
    suspend fun getDetailsByName(current: String): List<CityWeatherData>

    @Delete
    suspend fun deleteItem(data: CityWeatherData)

}