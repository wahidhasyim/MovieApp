package com.wahidhasyim.movieapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.wahidhasyim.movieapp.data.entities.MovieItem

class MovieComparator : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return newItem == oldItem
    }
}