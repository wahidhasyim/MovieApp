package com.wahidhasyim.movieapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.entities.Review
import com.wahidhasyim.movieapp.data.network.response.PaginationResponse

interface MovieUseCase {

    suspend fun popularMovie(page: Int): PaginationResponse<MovieItem>
    suspend fun topRatedMovie(page: Int): PaginationResponse<MovieItem>
    suspend fun nowPlayingMovie(page: Int): PaginationResponse<MovieItem>
    suspend fun ratingMovie(movieId: Long, page: Int): PaginationResponse<Review>
    fun favoriteMovie(): DataSource.Factory<Int, MovieItem>
    suspend fun addFavorite(movieItem: MovieItem)
    suspend fun deleteFavorite(movieItem: MovieItem)
    fun getMovie(id: Long): LiveData<MovieItem?>

}