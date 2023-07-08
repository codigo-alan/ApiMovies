package com.example.apimovies.retrofit

import com.example.apimovies.model.Data
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {


    @GET()
    suspend fun getData(@Url url: String): Response<Data>

    companion object {
        //private const val BASE_URL = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc/"
        private const val BASE_URL = "https://api.themoviedb.org/3/discover/"
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

    /*companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/discover/movie?/"

        suspend fun getFromApi(urlParams: String): okhttp3.Response {
            return withContext(Dispatchers.IO) {
                val request = Request.Builder()
                    .url("${BASE_URL}${urlParams}")
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1OWU4ZWM1NzE5NGFlOWYwNDZmZmM1NGQ4Y2M4MTg4OCIsInN1YiI6IjY0YTY4NWI3MDM5OGFiMDE0ZWRmMzhmZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qEV7qZdfQYJmgVvoaGmK85nWJoKjZK2REICj7EwrlKM")
                    .build()

                OkHttpClient().newCall(request).execute()
            }
        }
    }*/



}