package com.example.apimovies.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apimovies.R
import com.example.apimovies.adapters.MovieAdapter
import com.example.apimovies.adapters.OnClickListener
import com.example.apimovies.databinding.FragmentListBinding
import com.example.apimovies.model.Movie
import com.example.apimovies.viewmodel.ListViewModel
import com.squareup.picasso.Picasso


class ListFragment : Fragment(), OnClickListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private val model: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter(model.movies.value!!, this)

        linearLayoutManager = GridLayoutManager(context, 3)
        binding.recyclerListMovies.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = movieAdapter
        }

        model.movies.observe(viewLifecycleOwner){
            movieAdapter.setMovies(it)
        }

        model.successfulQuery.observe(viewLifecycleOwner){
            if (it && model.movies.value!!.isEmpty()) {
                binding.gridLayout.visibility = View.GONE
                binding.recyclerListMovies.visibility = View.GONE
                binding.noDataTv.visibility = View.VISIBLE
            }
            if (it && model.movies.value!!.isNotEmpty()) {
                binding.gridLayout.visibility = View.GONE
                binding.recyclerListMovies.visibility = View.VISIBLE
                binding.noDataTv.visibility = View.GONE
            }
            if (!it) {
                binding.gridLayout.visibility = View.GONE
                binding.errorDataTv.visibility = View.VISIBLE
            }
        }

        binding.filterEt.addTextChangedListener { userFilter ->
            val moviesEdited = model.movies.value?.filter { movie ->
                movie.originalTitle.lowercase().contains(userFilter.toString().lowercase()) }
            movieAdapter.setMovies(moviesEdited ?: listOf())
        }



    }

    override fun onClick(movie: Movie) {
        model.setSelectedMovie(movie)
        findNavController().navigate(R.id.action_listFragment_to_detailFragment)
    }

}