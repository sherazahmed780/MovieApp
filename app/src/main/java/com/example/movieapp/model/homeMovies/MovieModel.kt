package com.example.movieapp.model.homeMovies

data class MovieModel(

    val page: Int,
    val results: List<Results>,
    val total_pages: Int,
    val total_results: Int
)

