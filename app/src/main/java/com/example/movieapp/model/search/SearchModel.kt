package com.example.movieapp.model.search

import com.example.movieapp.model.homeMovies.Results

data class SearchModel(

	val page: Int,
	val results: List<Results>,
	val total_pages: Int,
	val total_results: Int,
)