package com.wahidhasyim.movieapp.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wahidhasyim.movieapp.BuildConfig
import com.wahidhasyim.movieapp.R
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.databinding.ItemMovieFavoriteBinding
import com.wahidhasyim.movieapp.presentation.MovieComparator
import com.wahidhasyim.movieapp.util.displayYear
import com.wahidhasyim.movieapp.util.loadImage
import com.wahidhasyim.movieapp.util.toDateOrNull

class FavoriteAdapter(private val listener: Listener) : PagingDataAdapter<MovieItem, RecyclerView.ViewHolder>(
    MovieComparator()
) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_movie_favorite
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)!!
        (holder as ItemMovieFavoriteHolder).apply {
            binding.actvTitle.text = data.title
            binding.actvYear.text = data.releaseDate.toDateOrNull()?.displayYear
            binding.actvRating.text = "${data.voteAverage}/10"
            binding.actvOverview.text = data.overview
            binding.acivThumbnail.loadImage(BuildConfig.BASE_URL_IMG + data.posterPath)
            binding.root.setOnClickListener { listener.onItemClick(data) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieFavoriteHolder {
        val binding = ItemMovieFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieFavoriteHolder(binding)
    }

    class ItemMovieFavoriteHolder(val binding: ItemMovieFavoriteBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    interface Listener {

        fun onItemClick(item: MovieItem)
    }
}