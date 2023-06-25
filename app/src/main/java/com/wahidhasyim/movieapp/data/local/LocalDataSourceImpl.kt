package com.wahidhasyim.movieapp.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.local.dao.FavoriteMovieDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
): LocalDataSource {


    override fun getFavoriteMovie(): DataSource.Factory<Int, MovieItem> {
        return favoriteMovieDao.getFavoriteMovie()
    }

    override suspend fun addFavoriteMovie(movieItem: MovieItem) {
        favoriteMovieDao.addFavorite(movieItem)
    }

    override suspend fun deleteFavoriteMovie(movieItem: MovieItem) {
        favoriteMovieDao.deleteFavorite(movieItem)
    }

    override fun getMovie(id: Long): LiveData<MovieItem?> {
        return favoriteMovieDao.getMovieById(id)
    }
}