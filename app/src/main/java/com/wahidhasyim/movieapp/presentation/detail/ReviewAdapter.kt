package com.wahidhasyim.movieapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wahidhasyim.movieapp.R
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.entities.Review
import com.wahidhasyim.movieapp.databinding.ItemReviewBinding
import com.wahidhasyim.movieapp.util.ago
import com.wahidhasyim.movieapp.util.loadImage
import com.wahidhasyim.movieapp.util.toDateOrNull

class ReviewAdapter() : PagingDataAdapter<Review, RecyclerView.ViewHolder>(
    ReviewComparator
) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_popular_movie
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)!!
        (holder as ItemReviewMovieHolder).apply {
            binding.acrbRating.rating = data.author.rating.toFloat()
            binding.actvName.text = data.author.name.ifBlank { "Anonymous" }
            binding.actvUsername.text = "@${data.author.username}"
            binding.tvDesc.text = data.content
            binding.actvDate.text = data.updatedAt.toDateOrNull(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")?.ago
            data.author.avatar.let {
                if (!it.isNullOrBlank()) {
                    binding.acivUser.loadImage(it.removePrefix("/"), makeItCircle = true)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemReviewMovieHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemReviewMovieHolder(binding)
    }

    class ItemReviewMovieHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    companion object {

        private val ReviewComparator = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
                oldItem == newItem
        }
    }
}