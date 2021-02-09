package com.example.itunesapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtistDetails(artistEntity: ArtistEntity)

    @Query("Select *from artist_table where artistName=:name")
    suspend fun searchArtist(name:String):List<ArtistEntity>
}