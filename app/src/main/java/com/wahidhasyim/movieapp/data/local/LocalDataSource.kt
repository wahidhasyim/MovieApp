package com.wahidhasyim.movieapp.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.wahidhasyim.movieapp.data.entities.MovieItem

interface LocalDataSource {
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieItem>
    suspend fun addFavoriteMovie(movieItem: MovieItem)
    suspend fun deleteFavoriteMovie(movieItem: MovieItem)
    fun getMovie(id: Long): LiveData<MovieItem?>
}