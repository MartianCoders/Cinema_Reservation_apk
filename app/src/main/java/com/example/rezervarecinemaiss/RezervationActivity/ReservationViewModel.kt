package com.example.rezervarecinemaiss.RezervationActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rezervarecinemaiss.Data.Movie
import com.example.rezervarecinemaiss.Data.Reservation
import com.example.rezervarecinemaiss.Data.User
import com.example.rezervarecinemaiss.Database.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(private val repository: RoomRepository):
    ViewModel() {

    var movieData: MutableLiveData<List<Movie>> = MutableLiveData()
    var reservationData: MutableLiveData<List<Reservation>> = MutableLiveData()

    init {
        loadRecords()
    }

    fun getRecordsObserver(): MutableLiveData<List<Movie>> {
        return movieData
    }

    private fun loadRecords() {
        val list = repository.getAllMovies()

        movieData.postValue(list)
    }

    fun insertMovie(movie: Movie) {
        repository.insertMovie(movie)
    }

    fun nukeMoviesTable() {
        repository.nukeMovieTable()
    }

    fun insertReservation(reservation: Reservation) {
        repository.addReservation(reservation)
    }

    fun getReservations(): MutableLiveData<List<Reservation>> {
        val list = repository.getAllReservations()
        reservationData.postValue(list)
        return reservationData
    }

    fun nukeReservationsTable() {
        repository.nukeReservationTable()
    }

}