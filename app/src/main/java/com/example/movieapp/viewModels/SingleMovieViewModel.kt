package com.example.movieapp.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.singleMovie.SingleMovieModel
import com.example.movieapp.repository.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

@HiltViewModel
class SingleMovieViewModel @Inject constructor(val movieRepo: MovieRepo) : ViewModel() {


    var getSingleData: MutableLiveData<SingleMovieModel> = MutableLiveData()

    fun getSingleMovie(movieID: String) = viewModelScope.launch {

        movieRepo.getSingleMovieData(movieID)
            .onStart {

            }
            .onCompletion {

            }
            .catch {

            }
            .collect {

                getSingleData.value = it
            }

    }


}