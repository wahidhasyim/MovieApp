package com.wahidhasyim.movieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.local.dao.FavoriteMovieDao

@Database(entities = [MovieItem::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteMovieDao
}