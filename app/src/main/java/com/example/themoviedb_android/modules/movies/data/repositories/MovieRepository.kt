package com.example.themoviedb_android.modules.movies.data.repositories

import com.example.themoviedb_android.modules.movies.data.datasources.RemoteMovieDataSource
import com.example.themoviedb_android.modules.movies.domain.Movie
import io.reactivex.Single

class MovieRepository(
    private val remoteMovieDataSource: RemoteMovieDataSource
) {

    fun getAllMovies(page: Int) = remoteMovieDataSource.getAllMovies(page)

}