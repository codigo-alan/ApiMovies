package com.example.apimovies.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("original_language") val originalLanguage: String,
    val popularity: Double,
    @SerializedName("release_date") val releaseDate: String,
    val overview: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("genre_ids") val genres: List<Int>,
    @SerializedName("poster_path") val posterPath: String
)

