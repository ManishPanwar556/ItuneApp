package com.example.itunesapp.repository

import com.example.itunesapp.retrofit.ItunesApiService
import com.example.itunesapp.room.ArtistDao
import com.example.itunesapp.room.ArtistEntity
import javax.inject.Inject


class ItunesRepository
@Inject constructor(
    private val itunesApiService: ItunesApiService,
    private val artistDao: ArtistDao
) {

    suspend fun getArtist(searchTerm: String) {
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

    suspend fun getArtistFromRoom(searchTerm: String): List<ArtistEntity> {
        return artistDao.searchArtist(searchTerm)
    }
}