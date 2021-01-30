package com.example.themoviedb_android.framework.requestmanager.apimappers

import com.example.themoviedb_android.framework.requestmanager.apiresponseserver.MovieResponseServer
import com.example.themoviedb_android.modules.movies.domain.Movie

fun MovieResponseServer.toMovieDomainList(): List<Movie> = results.map {
    it.run {
        Movie(
            id,
            title,
            overview,
            posterPath
        )
    }
}