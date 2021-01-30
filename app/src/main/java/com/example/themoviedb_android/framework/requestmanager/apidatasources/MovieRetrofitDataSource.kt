package com.example.themoviedb_android.framework.requestmanager.apidatasources

import com.example.themoviedb_android.framework.requestmanager.apimappers.toMovieDomainList
import com.example.themoviedb_android.framework.requestmanager.apirequests.MovieRequest
import com.example.themoviedb_android.framework.requestmanager.apiresponseserver.MovieResponseServer
import com.example.themoviedb_android.framework.requestmanager.apiservices.MovieService
import com.example.themoviedb_android.modules.movies.data.datasources.RemoteMovieDataSource
import com.example.themoviedb_android.modules.movies.domain.Movie
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRetrofitDataSource(
    private val movieRequest: MovieRequest
): RemoteMovieDataSource {

    override fun getAllMovies(page: Int): Single<List<Movie>> {
        return movieRequest
            .getService<MovieService>()
            .getAllMovies(page, "39c8af238a6bdfbe33629010bb758d12")
            .map(MovieResponseServer::toMovieDomainList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}