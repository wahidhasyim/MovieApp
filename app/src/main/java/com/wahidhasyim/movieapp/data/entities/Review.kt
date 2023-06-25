package com.wahidhasyim.movieapp.data.entities

import com.google.gson.annotations.SerializedName

data class Review(
    val id: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("author_details") val author: Author,
    val content: String
)

data class Author(
    val name: String,
    val username: String,
    @SerializedName("avatar_path") val avatar: String?,
    val rating: Int
)
