package com.example.themoviedb_android.modules.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.themoviedb_android.R
import com.example.themoviedb_android.modules.moviedetail.MovieDetailActivity
import com.example.themoviedb_android.modules.movies.configurator.MoviesListBuilder
import com.example.themoviedb_android.modules.movies.domain.Movie
import com.example.themoviedb_android.modules.movies.domain.parcelable.toMovieParcelable
import com.example.themoviedb_android.modules.movies.presentation.MoviesListViewModel
import com.example.themoviedb_android.modules.movies.ui.adapters.MovieListAdapter
import com.example.themoviedb_android.utils.Constants
import com.example.themoviedb_android.utils.extensions.setItemDecorationSpacing
import com.example.themoviedb_android.utils.extensions.showLongToast
import com.example.themoviedb_android.utils.extensions.startActivity
import com.example.themoviedb_android.utils.helpers.Event
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieListAdapter: MovieListAdapter

    private val movieListViewModel: MoviesListViewModel by lazy {
        MoviesListBuilder.createModule()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAdapter()
        movieListViewModel.getAllMovies()
        setupBindings()
    }

    private fun setupAdapter() {
        movieListAdapter = MovieListAdapter { movie ->
            openMovieDetail(movie)
        }

        movieListAdapter.setHasStableIds(true)

        rvMovieList.run {
            setItemDecorationSpacing(resources.getDimension(R.dimen.list_item_padding))
            adapter = movieListAdapter
        }
    }

    private fun setupBindings() {
        movieListViewModel.events.observe(this, Observer(this::validateEvents))
    }

    private fun validateEvents(event: Event<MoviesListViewModel.MovieListNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when(navigation) {
                is MoviesListViewModel.MovieListNavigation.ShowMovieError -> navigation.run {
                    showLongToast(error.localizedMessage)
                }
                is MoviesListViewModel.MovieListNavigation.ShowMovieList -> navigation.run {
                    movieListAdapter.updateData(movieList)
                }
                MoviesListViewModel.MovieListNavigation.HideLoading -> {
                    rlBaseMovies.visibility = View.INVISIBLE
                }
                MoviesListViewModel.MovieListNavigation.ShowLoading -> {
                    rlBaseMovies.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun openMovieDetail(movie: Movie) {
        startActivity<MovieDetailActivity> {
            putExtra(Constants.EXTRA_CHARACTER, movie.toMovieParcelable())
        }
    }

}