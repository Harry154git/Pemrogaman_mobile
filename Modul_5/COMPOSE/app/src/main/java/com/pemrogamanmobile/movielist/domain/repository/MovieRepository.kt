package com.pemrogamanmobile.movielist.domain.repository

import com.pemrogamanmobile.movielist.domain.model.Movie
import com.pemrogamanmobile.movielist.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<NetworkResult<List<Movie>>>
}