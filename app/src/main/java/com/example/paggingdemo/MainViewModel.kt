package com.example.paggingdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paggingdemo.model.Result

class MainViewModel(private val repository: Repository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()

    fun getMovieList(): LiveData<PagingData<Result>> {
        return repository.getAllMovies().cachedIn(viewModelScope)
    }
}