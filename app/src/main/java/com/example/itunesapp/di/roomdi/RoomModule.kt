package com.example.itunesapp.di.roomdi

import android.content.Context
import androidx.room.Room
import com.example.itunesapp.room.ArtistDao
import com.example.itunesapp.room.ArtistDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDao(artistDatabase: ArtistDatabase): ArtistDao {
        return artistDatabase.artistDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ArtistDatabase {
        return Room.databaseBuilder(context, ArtistDatabase::class.java, ArtistDatabase.DBNAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}