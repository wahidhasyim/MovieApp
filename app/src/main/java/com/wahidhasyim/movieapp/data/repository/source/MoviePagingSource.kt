package com.wahidhasyim.movieapp.data.repository.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wahidhasyim.movieapp.data.entities.MovieItem
import com.wahidhasyim.movieapp.data.network.response.PaginationResponse

class MoviePagingSource(
    private val networkCall: suspend (page: Int) -> PaginationResponse<MovieItem>
) : PagingSource<Int, MovieItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {

        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = networkCall.invoke(nextPage)

            LoadResult.Page(
                data = movieListResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1 ,
                nextKey = if (nextPage < movieListResponse.totalPages)
                    movieListResponse.page.plus(1) else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? { return 0 }
}