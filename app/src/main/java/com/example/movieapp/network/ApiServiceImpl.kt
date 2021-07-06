package com.example.movieapp.network

import com.example.movieapp.model.singleMovie.SingleMovieModel
import com.example.movieapp.utils.BaseUrls
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getSingleMovie( movieID:String): SingleMovieModel =
        apiService.getSingleMovie(movieID,BaseUrls.API_KEY)


}