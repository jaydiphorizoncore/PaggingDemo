package com.example.paggingdemo

import androidx.paging.PagingSource
import androidx.paging.PagingState

class MoviePagingSource(private val apiInterface: ApiInterface) :
    PagingSource<Int, com.example.paggingdemo.model.Result>() {
    override fun getRefreshKey(state: PagingState<Int, com.example.paggingdemo.model.Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.example.paggingdemo.model.Result> {
        return try {
            val position = params.key ?: 1
            val response =
                apiInterface.getMovies("e8d648003bd11b5c498674fbd4905525", "en-US", position)
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}