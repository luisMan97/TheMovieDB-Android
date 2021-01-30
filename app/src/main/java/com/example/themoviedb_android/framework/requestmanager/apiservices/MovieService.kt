package com.example.themoviedb_android.framework.requestmanager.apiservices

import com.example.themoviedb_android.framework.requestmanager.apiresponseserver.MovieResponseServer
import com.example.themoviedb_android.framework.requestmanager.constants.APIConstants.ENDPOINT_MOVIES
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(ENDPOINT_MOVIES)
    fun getAllMovies(
        @Path("page") page: Int,
        @Query("api_key") apiKey: String
    ): Single<MovieResponseServer>

}