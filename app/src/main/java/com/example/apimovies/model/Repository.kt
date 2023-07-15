package com.example.apimovies.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.apimovies.retrofit.ApiInterface


//private const val API_KEY = "api_key=ef145c123c58c057b804fd3592b12256"
private const val API_KEY = "ef145c123c58c057b804fd3592b12256"
class Repository {
    private val apiInterface = ApiInterface.create()

    var movies = MutableLiveData<List<Movie>>()
    val successfulQuery = MutableLiveData<Boolean>()
    val genres = MutableLiveData<List<Genre>>()

    suspend fun fetchData() {
        val response = apiInterface.getData(API_KEY)
        if(response.isSuccessful) {
            movies.postValue(response.body()!!.movies)
            Log.d("apiResponse", "${response.body()!!.movies}") //for dev
            successfulQuery.postValue(true)
        }
        else{
            movies.postValue(listOf())
            successfulQuery.postValue(false)
        }
    }

    suspend fun fetchGenres() {
        val response = apiInterface.getAllGenres(API_KEY)
        if(response.isSuccessful) {
            genres.postValue(response.body()!!.genres)
            Log.d("apiResponseGenres", "${response.body()!!.genres}") //for dev
        }
        else{
            genres.postValue(listOf())
        }
    }

}