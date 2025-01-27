package com.fragments.ombd_api.API

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OMBdAPIService {

    @GET("/")
    suspend fun getMovies(

        @Query("apikey") apiKey: String,
        @Query("s") buscador: String

    ): Response<OMBdApiResponse>

}