package uz.isytem.themoviedb.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Articles::class], version = 1)
abstract class RoomDataBase : RoomDatabase() {

    companion object {
        private var db: RoomDataBase? = null
        fun init(context: Context) {
            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    RoomDataBase::class.java,
                    "SavedMovies"
                ).allowMainThreadQueries().build()
            }
        }
        fun getInstance() = db?.getDao()
    }

    abstract fun getDao(): MovieDao

}