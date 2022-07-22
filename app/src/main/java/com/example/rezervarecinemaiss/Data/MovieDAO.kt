package com.example.rezervarecinemaiss.Data

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.rxjava3.core.Completable

// @Delete - stergere
// @Update - update element

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: Movie)

    @Query("SELECT * FROM movie_table")
    fun readAllMovies(): List<Movie>

    @Query("DELETE FROM movie_table")
    fun nukeTable()
}