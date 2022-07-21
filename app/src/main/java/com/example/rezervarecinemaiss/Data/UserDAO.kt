package com.example.rezervarecinemaiss.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addUser(user: User)

    @Query("SELECT * FROM user_table")
    fun readAllUsers():List<User>

    @Query("DELETE FROM user_table")
    fun nukeTable()
}