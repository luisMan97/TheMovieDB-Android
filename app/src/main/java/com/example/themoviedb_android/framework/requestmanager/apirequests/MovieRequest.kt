package com.example.themoviedb_android.framework.requestmanager.apirequests

import com.example.themoviedb_android.framework.requestmanager.apiservices.MovieService

class MovieRequest(baseUrl: String): BaseRequest<MovieService>(baseUrl)