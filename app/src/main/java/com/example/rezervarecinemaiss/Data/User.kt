package com.example.rezervarecinemaiss.Data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(tableName = "user_table", indices = [Index(value = ["username"], unique = true)])
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
    val admin: Boolean = false
)