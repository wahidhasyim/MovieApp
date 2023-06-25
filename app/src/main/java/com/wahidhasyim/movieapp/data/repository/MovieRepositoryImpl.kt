package com.wahidhasyim.movieapp.data.repository

import androidx.lifecycle.LiveData
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.entities.Review
import com.wahidhasyim.movieapp.data.local.LocalDataSource
import com.wahidhasyim.movieapp.data.network.response.DataResult
import com.wahidhasyim.movieapp.data.network.response.PaginationResponse
import com.wahidhasyim.movieapp.data.remote.RemoteDataSource
import com.wahidhasyim.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): MovieRepository {

    override suspend fun popularMovie(page: Int): PaginationResponse<MovieItem> {
        return when(val result = remoteDataSource.getPopularMovie(page)){
            is DataResult.Success -> result.data
            is DataResult.Error -> throw result.error
        }
    }

    override suspend fun topRatedMovie(page: Int): PaginationResponse<MovieItem> {
        return when(val result = remoteDataSource.getTopRatedMovie(page)) {
            is DataResult.Success -> result.data
            is DataResult.Error -> throw result.error
        }
    }

    override suspend fun nowPlayingMovie(page: Int): PaginationResponse<MovieItem> {
        return when(val result = remoteDataSource.getNowPlayingMovie(page)) {
            is DataResult.Success -> result.data
            is DataResult.Error -> throw result.error
        }
    }

    override suspend fun reviewMovie(movieId: Long, page: Int): PaginationResponse<Review> {
        return when(val result = remoteDataSource.getMovieReviews(movieId, page)) {
            is DataResult.Success -> result.data
            is DataResult.Error -> throw result.error
        }
    }

    override fun getFavoriteMovie() = localDataSource.getFavoriteMovie()

    override suspend fun addFavoriteMovie(movieItem: MovieItem) {
        localDataSource.addFavoriteMovie(movieItem)
    }

    override suspend fun deleteFavoriteMovie(movieItem: MovieItem) {
        localDataSource.deleteFavoriteMovie(movieItem)
    }

    override fun getMovie(id: Long): LiveData<MovieItem?> {
        return localDataSource.getMovie(id)
    }
}