package com.example.apimovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apimovies.model.Movie
import com.example.apimovies.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel: ViewModel() {
    private val _repository = Repository()

    private val _movies = MutableLiveData<List<Movie>>().apply { value = listOf() }
    val movies: LiveData<List<Movie>> = _movies

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> = _selectedMovie

    private val _successfulQuery = MutableLiveData<Boolean>()
    val successfulQuery: LiveData<Boolean> = _successfulQuery

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                //obtain from repo the data obtained from API
                _repository.fetchData("movie?")
            }
            _movies.postValue(_repository.movies.value)
            _successfulQuery.postValue(_repository.successfulQuery.value)
        }
    }

    fun setSelectedMovie(clickedMovie: Movie){
        _selectedMovie.postValue(clickedMovie)
    }
}