package com.example.apimovies.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apimovies.R
import com.example.apimovies.adapters.MovieAdapter
import com.example.apimovies.adapters.OnClickListener
import com.example.apimovies.databinding.FragmentListBinding
import com.example.apimovies.model.Movie
import com.example.apimovies.viewmodel.ListViewModel


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

        linearLayoutManager = LinearLayoutManager(context)
        binding.recyclerListMovies.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = movieAdapter
        }

        model.movies.observe(viewLifecycleOwner){
            movieAdapter.setMovies(it)
        }

    }

    override fun onClick(movie: Movie) {
        Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show()
        model.setSelectedMovie(movie)
        findNavController().navigate(R.id.action_listFragment_to_detailFragment)
    }

}