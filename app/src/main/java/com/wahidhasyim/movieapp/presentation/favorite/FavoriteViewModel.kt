package com.wahidhasyim.movieapp.presentation.favorite

import androidx.paging.PagingData
import com.wahidhasyim.movieapp.data.entities.MovieItem
import kotlinx.coroutines.flow.Flow

interface FavoriteViewModel {
    suspend fun movieFavoriteSource(): Flow<PagingData<MovieItem>>
}