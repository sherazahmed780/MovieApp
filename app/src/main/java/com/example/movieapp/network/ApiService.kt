package com.example.movieapp.network

import com.example.movieapp.model.homeMovies.MovieModel
import com.example.movieapp.model.singleMovie.SingleMovieModel

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apikey: String, @Query("page") pageNo: Int)
            : MovieModel


    @GET("movie/{id}}")
    suspend fun getSingleMovie(@Path("id") movieID: String,@Query("api_key") apikey: String)
            : SingleMovieModel


}