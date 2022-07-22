package com.example.rezervarecinemaiss.Data

import androidx.room.*


// @Ignore - ignora o variabila
// @Embedded - relatie intre tabele
// @ColumnInfo - Denumire coloana tabel

@Entity(tableName = "movie_table")
data class Movie (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val types: String,
    val time_elapsed: String,
    val date: String,
    val time: String,
    val photo: String,
)