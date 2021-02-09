package com.example.itunesapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArtistEntity::class],version = 1)
abstract class ArtistDatabase:RoomDatabase() {
    abstract fun artistDao():ArtistDao
    companion object{
        const val DBNAME="artist_db"
    }
}