package com.example.itunesapp.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiTest {
    lateinit var itunesApiService: ItunesApiService
    lateinit var gson: Gson
    @Before
    fun setUp() {
        gson = GsonBuilder().create()
        itunesApiService = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://itunes.apple.com/").build().create(ItunesApiService::class.java)
    }
    @Test
    fun search_Artist_Test(){
        runBlocking {
            val res=itunesApiService.getArtist("Arijit Singh")
            assertNotNull(res.body()?.results)
        }
    }

}