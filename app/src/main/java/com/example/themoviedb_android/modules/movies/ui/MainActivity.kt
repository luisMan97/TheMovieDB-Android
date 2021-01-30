package com.example.themoviedb_android.modules.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.themoviedb_android.R
import com.example.themoviedb_android.modules.movies.configurator.MoviesListBuilder
import com.example.themoviedb_android.modules.movies.presentation.MoviesListViewModel
import com.example.themoviedb_android.utils.helpers.Event

class MainActivity : AppCompatActivity() {

    private val movieListViewModel: MoviesListViewModel by lazy {
        MoviesListBuilder.createModule()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieListViewModel.getAllMovies()
        setupObservables()
    }

    private fun setupObservables() {
        movieListViewModel.events.observe(this, Observer(this::validateEvents))
    }

    private fun validateEvents(event: Event<MoviesListViewModel.MovieListNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when(navigation) {
                is MoviesListViewModel.MovieListNavigation.ShowMovieError -> navigation.run {
                    //context?.showLongToast("Error")
                    Log.e("MainActiovity", error.localizedMessage)
                    Log.e("MainActiovity", error.message)
                }
                is MoviesListViewModel.MovieListNavigation.ShowMovieList -> navigation.run {
                    //movieGridAdapter.addData(characterList)
                    Log.e("MainActiovity", movieList[0].title)
                }
                MoviesListViewModel.MovieListNavigation.HideLoading -> {
                    //srwCharacterList.isRefreshing = false
                    Log.e("MainActiovity", "Mostrar loading")
                }
                MoviesListViewModel.MovieListNavigation.ShowLoading -> {
                    //srwCharacterList.isRefreshing = true
                    Log.e("MainActiovity", "Ocultar loading")
                }
            }
        }
    }

}