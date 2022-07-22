package com.example.rezervarecinemaiss.Database

import com.example.rezervarecinemaiss.Data.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val userDAO: UserDAO,
    private val movieDAO: MovieDAO,
    private val reservationDAO: ReservationDAO,
) {

    fun getAllUsers(): List<User> {
        return userDAO.readAllUsers()
    }

    fun insertRecord(user: User) {
        userDAO.addUser(user)
    }

    fun nukeUserTable() {
        userDAO.nukeTable()
    }

    fun getAllMovies(): List<Movie> {
        return movieDAO.readAllMovies()
    }

    suspend fun insertMovie(movie: Movie) {
        return movieDAO.addMovie(movie)
    }

    fun nukeMovieTable() {
        movieDAO.nukeTable()
    }

    fun addReservation(reservation: Reservation) {
        reservationDAO.addReservation(reservation)
    }

    fun getAllReservations(): List<Reservation> {
        return reservationDAO.readAllReservations()
    }

    fun nukeReservationTable() {
        reservationDAO.nukeTable()
    }
}