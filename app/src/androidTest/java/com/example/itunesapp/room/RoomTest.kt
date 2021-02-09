package com.example.itunesapp.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomTest {
    lateinit var artistDatabase: ArtistDatabase
    lateinit var artistDao: ArtistDao

    @Before
    fun setUp() {
        artistDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ArtistDatabase::class.java
        ).allowMainThreadQueries().build()
        artistDao = artistDatabase.artistDao()
    }

    @After
    fun tearDown() {
        artistDatabase.close()
    }

    @Test
    fun check_inserting() {
        runBlocking {
            val artist = ArtistEntity("Arijit Singh", "Arijit Singh", "Tum hi ho", "123", 1234)
            artistDao.insertArtistDetails(artist)
            val res=artistDao.searchArtist("Arijit Singh")
            assertNotNull(res)
        }
    }
}