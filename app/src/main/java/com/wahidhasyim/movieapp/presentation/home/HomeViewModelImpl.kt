package com.wahidhasyim.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.network.exception.UnknownException
import com.wahidhasyim.movieapp.data.repository.source.MoviePagingSource
import com.wahidhasyim.movieapp.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val useCase: MovieUseCase
) : HomeViewModel, ViewModel() {

    override suspend fun movieSource(category: Int): Flow<PagingData<MovieItem>> {
        return Pager(config = PagingConfig(20)) {
            MoviePagingSource {
                when(category) {
                    0 -> useCase.popularMovie(it)
                    1 -> useCase.topRatedMovie(it)
                    2 -> useCase.nowPlayingMovie(it)
                    else -> throw UnknownException()
                }
            }
        }.flow.cachedIn(viewModelScope)
    }

}