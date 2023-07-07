package com.example.apimovies.model

data class Data(
    val page: Int,
    val movies: List<Movie>,
    val totalPages: Int,
    val totalMovies: Int
)