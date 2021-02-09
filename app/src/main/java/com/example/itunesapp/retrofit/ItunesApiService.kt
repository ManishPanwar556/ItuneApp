package com.example.itunesapp.retrofit

import com.example.itunesapp.retrofit.model.ArtistResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApiService {
    @GET("search")
    suspend fun getArtist(@Query("term") searchTerm:String=""):Response<ArtistResult>
}