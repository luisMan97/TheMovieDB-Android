package com.example.themoviedb_android.modules.movies.domain

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String
)