package com.example.apimovies.retrofit

import com.example.apimovies.model.Data
import com.example.apimovies.model.DataGenres
import com.example.apimovies.model.Genre
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {

    @GET("discover/movie")
    suspend fun getData(@Query("api_key") api_key: String): Response<Data>

    @GET("genre/movie/list")
    suspend fun getAllGenres(@Query("api_key") api_key: String): Response<DataGenres>

    companion object {
        //private const val BASE_URL = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc/"
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        fun create(): ApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }

    }

}