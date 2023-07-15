package com.example.apimovies.model

data class DataGenres(
    val genres: List<Genre>
)

data class Genre(
    val id: Int,
    val name: String
)
