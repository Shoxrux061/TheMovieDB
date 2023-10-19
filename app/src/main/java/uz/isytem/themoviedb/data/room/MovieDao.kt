package uz.isytem.themoviedb.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllSaved(): List<Articles>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovie(data: Articles)

    @Query("SELECT movie_id FROM movie")
    fun getAllIds():List<Int>

    @Query("DELETE FROM movie WHERE movie_id=:id")
    fun deleteById(id:Int)

    @Query("SELECT * FROM movie WHERE movie_id = :id")
    fun getMovieById(id: Int):Articles
}