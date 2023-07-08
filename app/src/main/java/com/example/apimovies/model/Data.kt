package com.example.apimovies.model

import com.google.gson.annotations.SerializedName

data class Data(
    val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalMovies: Int
)