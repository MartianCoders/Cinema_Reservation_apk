package com.example.rezervarecinemaiss.RezervationActivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rezervarecinemaiss.Data.Movie
import com.example.rezervarecinemaiss.Data.Reservation
import com.example.rezervarecinemaiss.Data.User
import com.example.rezervarecinemaiss.Database.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            repository.insertMovie(movie)
        }
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