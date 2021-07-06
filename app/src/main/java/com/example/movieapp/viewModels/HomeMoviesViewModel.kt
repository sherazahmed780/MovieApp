package com.example.movieapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.model.homeMovies.Results
import com.example.movieapp.network.ApiService
import com.example.movieapp.paging.MoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



@HiltViewModel
class HomeMoviesViewModel @Inject constructor(

    private val apiService: ApiService,
) : ViewModel() {

    val movies: Flow<PagingData<Results>> = Pager(PagingConfig(pageSize = 20)) {
        MoviePagingSource(apiService)
    }.flow
        .cachedIn(viewModelScope)


}

