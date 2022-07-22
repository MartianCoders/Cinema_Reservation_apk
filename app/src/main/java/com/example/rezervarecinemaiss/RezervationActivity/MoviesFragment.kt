package com.example.rezervarecinemaiss.RezervationActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rezervarecinemaiss.AccoutActivity.MainActivityViewModel
import com.example.rezervarecinemaiss.Data.Movie
import com.example.rezervarecinemaiss.Data.User
import com.example.rezervarecinemaiss.R
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable

@AndroidEntryPoint
class MoviesFragment: Fragment(){

    private val viewModel by viewModels<ReservationViewModel>()

    private lateinit var movieData: List<Movie>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movie1 = Movie(title = "Sully", types = "Biography, Drama", time_elapsed = "120min", date= "20/02/2022", time = "21:00", photo = "@drawable/movie_sully")
        viewModel.insertMovie(movie1)
        val movie6 = Movie(title = "The Revenant", types = "Adventure, Drama", time_elapsed = "120min", date= "24/02/2022", time = "21:99", photo = "@drawable/movie_the_revenant")
        viewModel.insertMovie(movie6)
        val movie2 = Movie(title = "Interstellar", types = "Sci-Fi, Action", time_elapsed = "140min", date= "20/02/2022", time = "18:30", photo = "@drawable/movie_interstellar")
        viewModel.insertMovie(movie2)
        val movie3 = Movie(title = "1917", types = "War, Drama", time_elapsed = "100min", date= "21/02/2022", time = "19:30", photo = "@drawable/movie_1917")
        viewModel.insertMovie(movie3)
        val movie4 = Movie(title = "The Lost Valentile", types = "Romantic, Drama", time_elapsed = "130min", date= "22/02/2022", time = "18:00", photo = "@drawable/movie_the_lost_valentine")
        viewModel.insertMovie(movie4)
        val movie5 = Movie(title = "The Batman", types = "Action, Drama, Heroes", time_elapsed = "180min", date= "23/02/2022", time = "20:15", photo = "@drawable/movie_the_batman")
        viewModel.insertMovie(movie5)





        subscribeObservers(view)

        super.onViewCreated(view, savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    private fun subscribeObservers(view: View) {
        viewModel.getRecordsObserver().observe(this, object: Observer<List<Movie>> {
            override fun onChanged(t: List<Movie>?) {
                movieData = t!!
                val movieRecyclerView: RecyclerView = view.findViewById(R.id.movies_recyclerview)
                movieRecyclerView.layoutManager = LinearLayoutManager(context)
                movieRecyclerView.isNestedScrollingEnabled = false
                val adapter = RecyclerViewMoviesAdapter(movieData)
                adapter.setHasStableIds(true)
                movieRecyclerView.adapter = adapter
            }
        })
    }
}