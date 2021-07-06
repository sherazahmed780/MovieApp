package com.example.movieapp.listeners

import android.view.View
import com.example.movieapp.model.homeMovies.Results

interface MovieItemClickListener{


    fun onMovieClick(view: View?, position: Int, movieData: Results)

}