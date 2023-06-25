package com.wahidhasyim.movieapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.entities.Review
import kotlinx.coroutines.flow.Flow

interface DetailViewModel {

    suspend fun reviewSource(movieId: Long): Flow<PagingData<Review>>
    fun addFavoriteMovie(movieItem: MovieItem)
    fun deleteFavoriteMovie(movieItem: MovieItem)
    fun isFavoriteMovie(): LiveData<Boolean>
}