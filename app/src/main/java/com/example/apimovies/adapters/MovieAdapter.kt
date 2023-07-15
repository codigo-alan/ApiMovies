package com.example.apimovies.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apimovies.R
import com.example.apimovies.databinding.ItemMovieBinding
import com.example.apimovies.model.Movie
import com.squareup.picasso.Picasso


class MovieAdapter(private var movies: List<Movie>, private val listener: OnClickListener): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemMovieBinding.bind(view)

        fun setListener(movie: Movie){
            binding.root.setOnClickListener {
                listener.onClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        with(holder){
            setListener(movie)
            binding.movieTitleTv.text = movie.originalTitle
            binding.movieReleaseTv.text = movie.releaseDate
            Picasso.get()
                .load("https://image.tmdb.org/t/p/original/${movie.posterPath}")
                .placeholder(R.drawable.edit_rounded)
                .error(R.drawable.baseline_broken_image_24)
                .fit()
                .centerCrop()
                .into(binding.imageView);
        }
    }


    fun setMovies(newListMovies: List<Movie>) {
        movies = newListMovies
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

