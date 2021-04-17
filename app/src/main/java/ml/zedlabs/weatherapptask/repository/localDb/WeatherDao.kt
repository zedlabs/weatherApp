package ml.zedlabs.weatherapptask.repository.localDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ml.zedlabs.weatherapptask.repository.models.CityWeatherData

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    suspend fun getAll(): List<CityWeatherData>

    @Insert
    suspend fun insertCityWeather(data: CityWeatherData)

//    @Query("SELECT * FROM notes WHERE title LIKE '%' || :titleParam || '%' ")
//    fun searchQuery(titleParam: String): Flow<List<Note>>
//
//    @Query("DELETE FROM notes")
//    suspend fun deleteAllNotes()
//
//    @Insert
//    suspend fun insertNote(note: Note)
//
//    @Delete
//    suspend fun deleteNote(note: Note)
//
//    @Update
//    suspend fun updateNote(note: Note)
//
//    @Query("SELECT * FROM notes WHERE id = :noteId")
//    fun findCityWeather(noteId: Int): Flow<Note>
}