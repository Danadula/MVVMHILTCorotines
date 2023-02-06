package com.mano.movieslistapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mano.movieslistapp.model.TvShowItem
import com.mano.movieslistapp.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _response = MutableLiveData<List<TvShowItem>>()

    val responseMoviesList: LiveData<List<TvShowItem>>
        get() = _response

    init {
        getAllMovies()
    }

    private fun getAllMovies() = viewModelScope.launch {
        repository.getTvShows().let { response ->

            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("tag", "getAllMovies Error: ${response.code()}")
            }
        }
    }

}