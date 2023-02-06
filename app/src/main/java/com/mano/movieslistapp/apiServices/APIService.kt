package com.mano.movieslistapp.apiServices

import com.mano.movieslistapp.constants.Constants
import com.mano.movieslistapp.model.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET(Constants.END_POINT)
    suspend fun getTvShows(): Response<TvShowResponse>

}
