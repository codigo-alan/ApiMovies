package com.example.apimovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apimovies.model.Genre
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

    private val _genres = MutableLiveData<List<Genre>>().apply { value = listOf() }
    val genres: LiveData<List<Genre>> = _genres

    init {
        fetchGenres()
        fetchData()
    }

    private fun fetchGenres() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                //obtain genres from API
                _repository.fetchGenres()
            }
            _genres.postValue(_repository.genres.value)
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                //obtain from repo the data obtained from API
                _repository.fetchData()
            }
            _movies.postValue(_repository.movies.value)
            _successfulQuery.postValue(_repository.successfulQuery.value)
        }
    }

    fun setSelectedMovie(clickedMovie: Movie){
        _selectedMovie.postValue(clickedMovie)
    }
}