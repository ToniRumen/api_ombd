package com.fragments.ombd_api.API


data class OMBdApiResponse(
    val Search: List<SearchMovie>
)


data class SearchMovie(
    val Title: String,
    val Year: String,
    val Poster: String
)

