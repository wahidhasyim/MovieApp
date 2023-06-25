package com.wahidhasyim.movieapp.data.remote

import com.wahidhasyim.movieapp.base.BaseService
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.entities.Review
import com.wahidhasyim.movieapp.data.network.response.DataResult
import com.wahidhasyim.movieapp.data.network.response.PaginationResponse
import com.wahidhasyim.movieapp.data.network.service.MovieService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
): RemoteDataSource, BaseService() {

    override suspend fun getPopularMovie(page: Int): DataResult<PaginationResponse<MovieItem>> {
        return createCall {
            service.getPopularMovie(page)
        }
    }

    override suspend fun getNowPlayingMovie(page: Int): DataResult<PaginationResponse<MovieItem>> {
        return createCall {
            service.getNowPlayingMovie(page)
        }
    }

    override suspend fun getTopRatedMovie(page: Int): DataResult<PaginationResponse<MovieItem>> {
        return createCall {
            service.getTopRatedMovie(page)
        }
    }

    override suspend fun getMovieReviews(movieId: Long, page: Int): DataResult<PaginationResponse<Review>> {
        return createCall {
            service.getReviewsMovie(movieId, page)
        }
    }
}