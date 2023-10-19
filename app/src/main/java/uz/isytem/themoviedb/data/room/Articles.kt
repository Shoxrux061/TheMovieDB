package uz.isytem.themoviedb.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Articles(
    @PrimaryKey
    @ColumnInfo("movie_id") val movieId:Int,
    @ColumnInfo("poster_path") val posterPath:String?,
    @ColumnInfo("filmName") val filmName:String,
    @ColumnInfo("title") val title:String
)