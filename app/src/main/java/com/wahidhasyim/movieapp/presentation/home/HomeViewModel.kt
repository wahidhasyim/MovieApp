package com.wahidhasyim.movieapp.presentation.home

import androidx.paging.PagingData
import com.wahidhasyim.movieapp.data.entities.MovieItem
import kotlinx.coroutines.flow.Flow

interface HomeViewModel {
    suspend fun movieSource(category: Int): Flow<PagingData<MovieItem>>
}