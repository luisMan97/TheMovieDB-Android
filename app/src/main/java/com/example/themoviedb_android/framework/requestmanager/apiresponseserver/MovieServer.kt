package com.example.themoviedb_android.framework.requestmanager.apiresponseserver

import android.os.Parcelable
import com.example.themoviedb_android.framework.requestmanager.constants.APIConstants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieServer(
    @SerializedName(APIConstants.KEY_ID) val id: Int,
    @SerializedName(APIConstants.KEY_TITLE) val title: String,
    @SerializedName(APIConstants.KEY_OVERVIEW) val overview: String,
    @SerializedName(APIConstants.KEY_POSTER_PATH) val posterPath: String
): Parcelable