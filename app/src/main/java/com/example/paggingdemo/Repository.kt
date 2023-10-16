package com.example.paggingdemo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.paggingdemo.model.Result

class Repository(private val apiInterface: ApiInterface) {

    fun getAllMovies(): LiveData<PagingData<Result>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiInterface)
            }, initialKey = 1
        ).liveData
        return pager
    }
}