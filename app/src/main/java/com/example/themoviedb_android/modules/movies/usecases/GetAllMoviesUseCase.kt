package com.example.themoviedb_android.modules.movies.usecases

import com.example.themoviedb_android.modules.movies.data.repositories.MovieRepository

class GetAllMoviesUseCase(
    private val movieRepository: MovieRepository
) {

    fun invoke(currentPage: Int) = movieRepository.getAllMovies(currentPage)

}