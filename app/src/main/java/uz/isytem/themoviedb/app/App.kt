package uz.isytem.themoviedb.app

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.hilt.android.HiltAndroidApp
import uz.isytem.themoviedb.data.room.RoomDataBase

@HiltAndroidApp
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        RoomDataBase.init(this)
        RoomDataBase.getInstance()
    }
}