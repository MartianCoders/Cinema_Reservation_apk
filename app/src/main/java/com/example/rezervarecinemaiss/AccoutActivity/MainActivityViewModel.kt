package com.example.rezervarecinemaiss.AccoutActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rezervarecinemaiss.Data.User
import com.example.rezervarecinemaiss.Database.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RoomRepository):
    ViewModel() {

    var userData: MutableLiveData<List<User>> = MutableLiveData()

    init {
        loadRecords()
    }

    fun getRecordsObserver(): MutableLiveData<List<User>> {
        return userData
    }

    private fun loadRecords() {
        val list = repository.getAllUsers()

        userData.postValue(list)
    }

    fun insertRecord(user: User) {
        repository.insertRecord(user)
    }

    fun nukeTable() {
        repository.nukeUserTable()
    }

}