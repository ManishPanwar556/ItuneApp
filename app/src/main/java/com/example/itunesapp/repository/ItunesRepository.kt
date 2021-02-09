package com.example.itunesapp.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.util.Log
import com.example.itunesapp.retrofit.ItunesApiService
import com.example.itunesapp.room.ArtistDao
import com.example.itunesapp.room.ArtistEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject


class ItunesRepository
@Inject constructor(
    private val itunesApiService: ItunesApiService,
    private val artistDao: ArtistDao
) {


    suspend fun getArtist(searchTerm: String) {
        try{
            val res = itunesApiService.getArtist(searchTerm)
            if (res.isSuccessful) {
                res.body()?.results?.forEach { ele ->
                    val artistEntity = ArtistEntity(
                        searchTerm,
                        ele.artistName,
                        ele.trackName,
                        ele.artworkUrl100,
                        ele.trackId
                    )
                    artistDao.insertArtistDetails(artistEntity)
                }
            }
        }
        catch (exc:IOException){
            Log.e("exc","$exc")
        }

    }

    suspend fun getArtistFromRoom(searchTerm: String): List<ArtistEntity> {
        return artistDao.searchArtist(searchTerm)
    }
}