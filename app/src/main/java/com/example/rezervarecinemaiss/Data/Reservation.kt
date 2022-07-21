package com.example.rezervarecinemaiss.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservation_table")
data class Reservation (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val movie: Int,
    val name: String,
    val phone: String,
    val seats_reservation: String
)