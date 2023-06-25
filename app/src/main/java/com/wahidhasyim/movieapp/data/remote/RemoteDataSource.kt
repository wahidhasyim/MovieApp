package com.wahidhasyim.movieapp.data.remote

import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.entities.Review
import com.wahidhasyim.movieapp.data.network.response.DataResult
import com.wahidhasyim.movieapp.data.network.response.PaginationResponse

interface RemoteDataSource {

    suspend fun getPopularMovie(page: Int): DataResult<PaginationResponse<MovieItem>>
    suspend fun getNowPlayingMovie(page: Int): DataResult<PaginationResponse<MovieItem>>
    suspend fun getTopRatedMovie(page: Int): DataResult<PaginationResponse<MovieItem>>
    suspend fun getMovieReviews(movieId: Long, page: Int): DataResult<PaginationResponse<Review>>
}