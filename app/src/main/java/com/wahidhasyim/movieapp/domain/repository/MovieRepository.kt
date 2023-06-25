package com.wahidhasyim.movieapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.entities.Review
import com.wahidhasyim.movieapp.data.network.response.PaginationResponse

interface MovieRepository {

    suspend fun popularMovie(page: Int): PaginationResponse<MovieItem>
    suspend fun topRatedMovie(page: Int): PaginationResponse<MovieItem>
    suspend fun nowPlayingMovie(page: Int): PaginationResponse<MovieItem>
    suspend fun reviewMovie(movieId: Long, page: Int): PaginationResponse<Review>
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieItem>
    suspend fun addFavoriteMovie(movieItem: MovieItem)
    suspend fun deleteFavoriteMovie(movieItem: MovieItem)
    fun getMovie(id: Long): LiveData<MovieItem?>
}