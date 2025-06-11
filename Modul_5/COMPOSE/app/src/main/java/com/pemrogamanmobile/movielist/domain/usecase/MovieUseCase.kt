package com.pemrogamanmobile.movielist.domain.usecase

import com.pemrogamanmobile.movielist.domain.model.Movie
import com.pemrogamanmobile.movielist.domain.repository.MovieRepository
import com.pemrogamanmobile.movielist.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class MovieUseCase(private val repository: MovieRepository) {
    fun getPopularMovies(): Flow<NetworkResult<List<Movie>>> {
        return repository.getPopularMovies()
    }
}