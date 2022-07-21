package com.example.rezervarecinemaiss.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReservationDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addReservation(reservation: Reservation)

    @Query("SELECT * FROM reservation_table")
    fun readAllReservations(): List<Reservation>

    @Query("DELETE FROM reservation_table")
    fun nukeTable()
}