package com.wahidhasyim.movieapp.data.network.service

import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.entities.Review
import com.wahidhasyim.movieapp.data.network.response.PaginationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("page") page: Int
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("page") page: Int
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewsMovie(
        @Path("movie_id") id: Long, @Query("page") page: Int
    ): Response<PaginationResponse<Review>>

}