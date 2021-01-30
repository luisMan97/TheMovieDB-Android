package com.example.themoviedb_android.modules.moviedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themoviedb_android.R
import com.example.themoviedb_android.framework.requestmanager.constants.APIConstants
import com.example.themoviedb_android.modules.movies.domain.Movie
import com.example.themoviedb_android.modules.movies.domain.parcelable.MovieParcelable
import com.example.themoviedb_android.modules.movies.domain.parcelable.toMovieDomain
import com.example.themoviedb_android.utils.Constants
import com.example.themoviedb_android.utils.extensions.bindImageUrl
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieDetailActivity : AppCompatActivity() {

    private var movieSelected: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        getMovie()
        setElements()
    }

    private fun getMovie() {
        movieSelected = intent.getParcelableExtra<MovieParcelable>(Constants.EXTRA_CHARACTER)?.toMovieDomain()
    }

    private fun setElements() {
        tvOverviewMovie.text = movieSelected?.overview
        tvTitleMovie.text = movieSelected?.title
        ivMovie.bindImageUrl(
            url = "${APIConstants.ENDPOINT_IMAGE}${movieSelected?.posterPath}",
            placeholder = R.drawable.ic_baseline_movie_24,
            errorPlaceholder = R.drawable.ic_broken_image_black
        )
    }

}