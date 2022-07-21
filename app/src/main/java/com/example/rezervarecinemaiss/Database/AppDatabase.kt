package com.example.rezervarecinemaiss.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rezervarecinemaiss.Data.*

@Database(entities = [User::class, Movie::class, Reservation::class], version = 4)
abstract class AppDatabase: RoomDatabase()  {

    abstract fun getUserDAO(): UserDAO
    abstract fun getMovieDAO(): MovieDAO
    abstract fun getReservationDAO(): ReservationDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Database"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }
}