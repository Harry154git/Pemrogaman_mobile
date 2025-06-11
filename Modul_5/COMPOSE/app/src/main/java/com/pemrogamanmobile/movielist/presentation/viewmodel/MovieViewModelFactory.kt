package com.pemrogamanmobile.movielist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pemrogamanmobile.movielist.domain.usecase.MovieUseCase

class MovieViewModelFactory(private val useCase: MovieUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}