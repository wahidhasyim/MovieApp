package com.wahidhasyim.movieapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahidhasyim.movieapp.data.entities.MovieItem

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM MovieItem ")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieItem>

    @Query("SELECT * FROM MovieItem WHERE id = :movieId")
    fun getMovieById(movieId: Long): LiveData<MovieItem?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(movieItem: MovieItem): Long

    @Delete
    suspend fun deleteFavorite(movieItem: MovieItem): Int

}