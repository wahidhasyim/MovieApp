package com.wahidhasyim.movieapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wahidhasyim.movieapp.BuildConfig
import com.wahidhasyim.movieapp.R
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.databinding.ItemPopularMovieBinding
import com.wahidhasyim.movieapp.util.loadImage

class MoviePopularAdapter(private val listener: Listener)
    : PagingDataAdapter<MovieItem, RecyclerView.ViewHolder>(
    MovieComparator()
) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_popular_movie
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)!!
        when (holder) {
            is ItemPopularMovieViewHolder -> {
                holder.binding.acivImage.loadImage(BuildConfig.BASE_URL_IMG+data.backdropPath, roundCorner = 10)
                holder.binding.root.setOnClickListener {
                    listener.onItemClick(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPopularMovieViewHolder {
        val binding = ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemPopularMovieViewHolder(binding)
    }

    class ItemPopularMovieViewHolder(val binding: ItemPopularMovieBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    interface Listener {
        fun onItemClick(item: MovieItem)
    }

}