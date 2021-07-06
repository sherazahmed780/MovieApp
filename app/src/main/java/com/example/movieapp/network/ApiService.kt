package com.example.movieapp.network

import com.example.movieapp.model.MovieModel

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apikey: String, @Query("page") pageNo: Int)
            : MovieModel

}