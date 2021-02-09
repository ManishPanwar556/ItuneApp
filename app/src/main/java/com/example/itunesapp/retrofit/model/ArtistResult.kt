package com.example.itunesapp.retrofit.model


import com.squareup.moshi.Json

data class ArtistResult(
    @Json(name = "resultCount")
    val resultCount: Int,
    @Json(name = "results")
    val results: List<Result>
)