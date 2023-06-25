package com.wahidhasyim.movieapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wahidhasyim.movieapp.BuildConfig
import com.wahidhasyim.movieapp.R
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.databinding.ItemMovieBinding
import com.wahidhasyim.movieapp.util.loadImage

class MovieAdapter(private val listener: Listener) : PagingDataAdapter<MovieItem, RecyclerView.ViewHolder>(
    MovieComparator()
) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_popular_movie
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)!!
        when (holder) {
            is ItemTopRatedMovieHolder -> {
                holder.binding.acivImage.loadImage(BuildConfig.BASE_URL_IMG + data.backdropPath, roundCorner = 10)
                holder.binding.actvTitle.text = data.title
                holder.binding.root.setOnClickListener {
                    listener.onItemClick(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTopRatedMovieHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemTopRatedMovieHolder(binding)
    }

    class ItemTopRatedMovieHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    interface Listener {

        fun onItemClick(item: MovieItem)
    }
}