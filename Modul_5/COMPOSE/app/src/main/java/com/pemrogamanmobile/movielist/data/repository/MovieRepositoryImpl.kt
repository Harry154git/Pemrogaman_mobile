package com.pemrogamanmobile.movielist.data.repository

import com.pemrogamanmobile.movielist.data.local.dao.MovieDao
import com.pemrogamanmobile.movielist.data.remote.api.ApiService
import com.pemrogamanmobile.movielist.domain.model.Movie
import com.pemrogamanmobile.movielist.domain.repository.MovieRepository
import com.pemrogamanmobile.movielist.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val api: ApiService,
    private val dao: MovieDao
) : MovieRepository {
    override fun getPopularMovies(): Flow<NetworkResult<List<Movie>>> = flow {
        emit(NetworkResult.Loading)
        try {
            val response = api.getPopularMovies("a9936cbd33c74fbfa33d892ad498af3c")
            val movies = response.results.map {
                Movie(it.id, it.title, it.overview, it.posterPath, it.releaseDate)
            }
            dao.clearMovies()
            dao.insertMovies(movies.map { entity ->
                com.pemrogamanmobile.movielist.data.local.entity.MovieEntity(
                    entity.id,
                    entity.title,
                    entity.overview,
                    entity.posterPath,
                    entity.releaseDate
                )
            })
            emit(NetworkResult.Success(movies))
        } catch (e: Exception) {
            val cached = dao.getAllMovies().map {
                Movie(it.id, it.title, it.overview, it.posterPath, it.releaseDate)
            }
            if (cached.isNotEmpty()) {
                emit(NetworkResult.Success(cached))
            } else {
                emit(NetworkResult.Error(e.message ?: "Unknown Error"))
            }
        }
    }
}