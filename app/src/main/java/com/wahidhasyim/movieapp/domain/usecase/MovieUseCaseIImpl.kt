package com.wahidhasyim.movieapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.entities.Review
import com.wahidhasyim.movieapp.data.network.response.PaginationResponse
import com.wahidhasyim.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCaseIImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {

    override suspend fun popularMovie(page: Int): PaginationResponse<MovieItem> =
        movieRepository.popularMovie(page)

    override suspend fun topRatedMovie(page: Int): PaginationResponse<MovieItem> =
        movieRepository.topRatedMovie(page)

    override suspend fun nowPlayingMovie(page: Int): PaginationResponse<MovieItem> =
        movieRepository.nowPlayingMovie(page)

    override suspend fun ratingMovie(movieId: Long, page: Int): PaginationResponse<Review> =
        movieRepository.reviewMovie(movieId, page)

    override fun favoriteMovie(): DataSource.Factory<Int, MovieItem> = movieRepository.getFavoriteMovie()

    override suspend fun addFavorite(movieItem: MovieItem) {
        movieRepository.addFavoriteMovie(movieItem)
    }

    override suspend fun deleteFavorite(movieItem: MovieItem) {
        movieRepository.deleteFavoriteMovie(movieItem)
    }

    override fun getMovie(id: Long): LiveData<MovieItem?> {
        return movieRepository.getMovie(id)
    }
}