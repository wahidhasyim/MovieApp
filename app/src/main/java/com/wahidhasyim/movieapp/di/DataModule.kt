package com.wahidhasyim.movieapp.di

import android.content.Context
import androidx.room.Room
import com.wahidhasyim.movieapp.data.local.LocalDataSource
import com.wahidhasyim.movieapp.data.local.LocalDataSourceImpl
import com.wahidhasyim.movieapp.data.local.dao.FavoriteMovieDao
import com.wahidhasyim.movieapp.data.local.database.AppDatabase
import com.wahidhasyim.movieapp.data.network.service.MovieService
import com.wahidhasyim.movieapp.data.remote.RemoteDataSource
import com.wahidhasyim.movieapp.data.remote.RemoteDataSourceImpl
import com.wahidhasyim.movieapp.data.repository.MovieRepositoryImpl
import com.wahidhasyim.movieapp.domain.repository.MovieRepository
import com.wahidhasyim.movieapp.domain.usecase.MovieUseCase
import com.wahidhasyim.movieapp.domain.usecase.MovieUseCaseIImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    fun provideDB(@ApplicationContext context: Context): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, "movie")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun provideLocalDataSource(
        favoriteMovieDao: FavoriteMovieDao
    ): LocalDataSource = LocalDataSourceImpl(
        favoriteMovieDao
    )

    @Singleton
    @Provides
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteMovieDao = appDatabase.favoriteDao()

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        service: MovieService
    ): RemoteDataSource = RemoteDataSourceImpl(
        service
    )

    @Provides
    @Singleton
    fun provideMovieUseCase(
        movieRepository: MovieRepository
    ): MovieUseCase = MovieUseCaseIImpl(
        movieRepository
    )

    @Provides
    @Singleton
    fun provideMovieRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): MovieRepository = MovieRepositoryImpl(
        remoteDataSource,
        localDataSource
    )


}