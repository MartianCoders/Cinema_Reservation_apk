package com.example.rezervarecinemaiss.RezervationActivity

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.rezervarecinemaiss.R
import com.squareup.picasso.Picasso

class RecyclerViewMoviesAdapter(
    private val dataSet: List<com.example.rezervarecinemaiss.Data.Movie>
): RecyclerView.Adapter<RecyclerViewMoviesAdapter.MovieViewHolder>() {


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieTitleTextView: TextView = itemView.findViewById(R.id.textview_movie_title)
        var movieTypeTextView: TextView = itemView.findViewById(R.id.textview_movie_type)
        var movieTimeElapsedTextView: TextView = itemView.findViewById(R.id.textview_movie_time_2)
        var movieDateTextView: TextView = itemView.findViewById(R.id.textview_movie_date)
        var movieTimeTextView: TextView = itemView.findViewById(R.id.textview_movie_time)
        var moviePhotoImageView: ImageView = itemView.findViewById(R.id.imageview_for_movie)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieTitleTextView.text = dataSet[position].title
        holder.movieTypeTextView.text = dataSet[position].types
        holder.movieTimeElapsedTextView.text = dataSet[position].time_elapsed
        holder.movieDateTextView.text = dataSet[position].date
        holder.movieTimeTextView.text = dataSet[position].time

        val context = holder.moviePhotoImageView.context
        Picasso.get().load(context.resources.getIdentifier(dataSet[position].photo, "drawable", context.packageName)).fit().centerCrop()
            .placeholder(R.color.white)
            .into(holder.moviePhotoImageView)

        holder.movieTimeTextView.setOnClickListener {
            val action = MoviesFragmentDirections.goToSeatsReservation(dataSet[position].id)
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}