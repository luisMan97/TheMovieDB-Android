package com.example.themoviedb_android.framework.requestmanager.apiresponseserver

import com.example.themoviedb_android.framework.requestmanager.constants.APIConstants.KEY_RESULTS
import com.google.gson.annotations.SerializedName

data class MovieResponseServer(
    @SerializedName(KEY_RESULTS) val results: List<MovieServer>
)
