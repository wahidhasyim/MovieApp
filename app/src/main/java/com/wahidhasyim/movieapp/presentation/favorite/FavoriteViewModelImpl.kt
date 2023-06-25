package com.wahidhasyim.movieapp.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class FavoriteViewModelImpl @Inject constructor(
    private val useCase: MovieUseCase
) : FavoriteViewModel, ViewModel() {

    override suspend fun movieFavoriteSource(): Flow<PagingData<MovieItem>> {
        return Pager(config = PagingConfig(20)) {
            useCase.favoriteMovie().asPagingSourceFactory().invoke()
        }.flow
    }

}