package com.wahidhasyim.movieapp.data.network.response

import com.google.gson.annotations.SerializedName

data class PaginationResponse<T>(
    val page: Int,
    val results: List<T>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)