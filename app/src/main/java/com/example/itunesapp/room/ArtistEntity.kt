package com.example.itunesapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist_table")
data class ArtistEntity(
    val queryName: String,
    val artistName: String,
    val trackName: String?,
    val photoUrl: String?,
    @PrimaryKey(autoGenerate = false)
    val trackId: Int
)