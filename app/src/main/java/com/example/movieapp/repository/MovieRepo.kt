package com.example.movieapp.repository

import com.example.movieapp.model.singleMovie.SingleMovieModel
import com.example.movieapp.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepo @Inject constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getSingleMovieData(movieID: String): Flow<SingleMovieModel> = flow {

        val response = apiServiceImpl.getSingleMovie(movieID)

        emit(response)

    }.flowOn(Dispatchers.IO)
        .conflate()


}