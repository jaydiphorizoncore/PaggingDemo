package com.example.paggingdemo

import com.example.paggingdemo.model.MoviesResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/top_rated")
    suspend fun getMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    companion object {
        private var apiInterface: ApiInterface? = null
        fun getInstance(): ApiInterface {
            if (apiInterface == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiInterface = retrofit.create(ApiInterface::class.java)
            }
            return apiInterface!!
        }

    }
}