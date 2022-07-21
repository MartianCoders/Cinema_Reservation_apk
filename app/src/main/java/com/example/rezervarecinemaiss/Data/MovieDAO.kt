package com.example.rezervarecinemaiss.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addMovie(movie: Movie)

    @Query("SELECT * FROM movie_table")
    fun readAllMovies():List<Movie>

    @Query("DELETE FROM movie_table")
    fun nukeTable()
}