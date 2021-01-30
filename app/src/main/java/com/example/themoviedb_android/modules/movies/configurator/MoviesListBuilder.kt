package com.example.themoviedb_android.modules.movies.configurator

import com.example.themoviedb_android.framework.requestmanager.apidatasources.MovieRetrofitDataSource
import com.example.themoviedb_android.framework.requestmanager.apirequests.MovieRequest
import com.example.themoviedb_android.framework.requestmanager.constants.APIConstants.BASE_API_URL
import com.example.themoviedb_android.modules.movies.data.datasources.RemoteMovieDataSource
import com.example.themoviedb_android.modules.movies.data.repositories.MovieRepository
import com.example.themoviedb_android.modules.movies.presentation.MoviesListViewModel
import com.example.themoviedb_android.modules.movies.usecases.GetAllMoviesUseCase

class MoviesListBuilder {

    companion object {
        fun createModule(): MoviesListViewModel {
            val movieRequest = MovieRequest(BASE_API_URL)
            val remoteMovieDataSource: RemoteMovieDataSource = MovieRetrofitDataSource(movieRequest)
            val movieRepository = MovieRepository(remoteMovieDataSource)
            val getAllMoviesUseCase = GetAllMoviesUseCase(movieRepository)
            return MoviesListViewModel(getAllMoviesUseCase)
        }
    }

}