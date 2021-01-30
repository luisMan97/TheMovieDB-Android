package com.example.themoviedb_android.modules.movies.domain.parcelable

import android.os.Parcelable
import com.example.themoviedb_android.modules.movies.domain.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieParcelable(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String
): Parcelable

fun Movie.toMovieParcelable()  = MovieParcelable(id, title, overview, posterPath)
fun MovieParcelable.toMovieDomain()  = Movie(id, title, overview, posterPath)

