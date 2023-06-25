package com.wahidhasyim.movieapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.entities.Review
import com.wahidhasyim.movieapp.data.repository.source.MovieReviewsPagingSource
import com.wahidhasyim.movieapp.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModelImpl @Inject constructor(
    private val useCase: MovieUseCase
): DetailViewModel, ViewModel() {

    override suspend fun reviewSource(movieId: Long): Flow<PagingData<Review>> {
        return Pager(config = PagingConfig(20)) {
            MovieReviewsPagingSource {
                useCase.ratingMovie(movieId, it)
            }
        }.flow.cachedIn(viewModelScope)
    }

    override fun addFavoriteMovie(movieItem: MovieItem) {
        viewModelScope.launch {
            useCase.addFavorite(movieItem)
        }
    }

    override fun deleteFavoriteMovie(movieItem: MovieItem) {
        viewModelScope.launch {
            useCase.deleteFavorite(movieItem)
        }
    }

    private val _id = MutableLiveData<Long>()

    override fun isFavoriteMovie(): LiveData<Boolean> {
        return _id.switchMap { useCase.getMovie(it) }.map { it != null }
    }

    fun loadData(movieId: Long) {
        _id.value = movieId
    }
}