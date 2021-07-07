package com.example.movieapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.model.homeMovies.Results


import com.example.movieapp.network.ApiService
import com.example.movieapp.paging.SearchMoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(

    private val apiService: ApiService,
) : ViewModel() {


    private lateinit var queryName: String

    val searchMovies: Flow<PagingData<Results>> = Pager(PagingConfig(pageSize = 20)) {
        SearchMoviePagingSource(apiService,queryName)
    }.flow
        .cachedIn(viewModelScope)

    fun queryName(searchedKeyword: String) {
        queryName = searchedKeyword

    }


}