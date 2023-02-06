package com.mano.movieslistapp.repositories

import com.mano.movieslistapp.apiServices.APIService
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val apiService: APIService) {
    suspend fun getTvShows() = apiService.getTvShows()

}