package com.pemrogamanmobile.movielist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemrogamanmobile.movielist.domain.model.Movie
import com.pemrogamanmobile.movielist.domain.usecase.MovieUseCase
import com.pemrogamanmobile.movielist.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    private val _movies = MutableStateFlow<NetworkResult<List<Movie>>>(NetworkResult.Loading)
    val movies: StateFlow<NetworkResult<List<Movie>>> get() = _movies

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            movieUseCase.getPopularMovies().collect {
                _movies.value = it
            }
        }
    }
}