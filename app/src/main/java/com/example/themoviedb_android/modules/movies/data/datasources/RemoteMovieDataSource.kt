package com.example.themoviedb_android.modules.movies.data.datasources

import com.example.themoviedb_android.modules.movies.domain.Movie
import io.reactivex.Single

interface RemoteMovieDataSource {

    fun getAllMovies(page: Int): Single<List<Movie>>

}