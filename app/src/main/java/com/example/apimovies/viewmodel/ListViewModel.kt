package com.example.apimovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apimovies.model.Movie
import com.example.apimovies.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel: ViewModel() {
    private val repository = Repository()
    var movies = MutableLiveData<List<Movie>>().apply { value = listOf() }

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                //obtain from repo the data obtained from API
                repository.fetchData("movie?")
            }
            movies.postValue(repository.movies.value)
        }
    }
}