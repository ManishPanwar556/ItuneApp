package com.example.itunesapp.viewModel

import androidx.lifecycle.*
import com.example.itunesapp.repository.ItunesRepository
import com.example.itunesapp.room.ArtistEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItunesViewModel
@Inject constructor(
    private val repository: ItunesRepository,
    private val savedStateHandle: SavedStateHandle
):ViewModel(){

    private val _artistList= MutableLiveData<List<ArtistEntity>>()
    val artistList:LiveData<List<ArtistEntity>>
    get() = _artistList

    fun searchArtist(searchTerm:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getArtist(searchTerm)
            val res=repository.getArtistFromRoom(searchTerm)
            _artistList.postValue(res)
        }
    }
}