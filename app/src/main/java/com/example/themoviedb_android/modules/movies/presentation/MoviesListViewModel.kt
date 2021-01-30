package com.example.themoviedb_android.modules.movies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb_android.modules.movies.domain.Movie
import com.example.themoviedb_android.modules.movies.usecases.GetAllMoviesUseCase
import com.example.themoviedb_android.utils.helpers.DisposablesManager
import com.example.themoviedb_android.utils.helpers.Event
import com.example.themoviedb_android.utils.helpers.Response
import io.reactivex.disposables.CompositeDisposable

class MoviesListViewModel(
    private val getAllCharactersUseCase: GetAllMoviesUseCase
): ViewModel() {

    private val disposablesManager: DisposablesManager by lazy { DisposablesManager() }

    private val disposable = CompositeDisposable()

    val scheduleLiveData: MutableLiveData<Response<Movie>> = MutableLiveData()
    private var _events = MutableLiveData<Event<MovieListNavigation>>()
    val events: LiveData<Event<MovieListNavigation>> get() = _events

    fun getAllMovies() {
        /*return disposablesManager.addDisposable(
            getAllCharactersUseCase.invoke(1),
            scheduleLiveData, "get_schedule"
        )*/

        disposable.add(
            getAllCharactersUseCase.invoke(1)
                .doOnSubscribe {
                    _events.value =
                        Event(
                            MovieListNavigation.ShowLoading
                        )
                }
                .subscribe({ movieList ->
                    /*if (characterList.size < PAGE_SIZE) {
                        isLastPage = true
                    }*/

                    _events.value =
                        Event(
                            MovieListNavigation.HideLoading
                        )
                    _events.value =
                        Event(
                            MovieListNavigation.ShowMovieList(movieList)
                        )
                }, { error ->
                    //isLastPage = true
                    _events.value =
                        Event(
                            MovieListNavigation.HideLoading
                        )
                    _events.value =
                        Event(
                            MovieListNavigation.ShowMovieError(error)
                        )
                })
        )
    }

    sealed class MovieListNavigation {
        data class ShowMovieError(val error: Throwable) : MovieListNavigation()
        data class ShowMovieList(val movieList: List<Movie>) : MovieListNavigation()
        object HideLoading: MovieListNavigation()
        object ShowLoading: MovieListNavigation()
    }

}