package com.example.youtubeapi.ui.playlistitems

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.data.model.PlayListItemsModel
import com.example.youtubeapi.data.repository.YouTubeRepository
import com.example.youtubeapi.data.service.Resource

class PlayListItemsViewModel(private val repository: YouTubeRepository): ViewModel(){
    fun getPlaylistItems(getId:String, count: Int): LiveData<Resource<List<PlayListItemsModel.Item>>> = repository.getPlaylistItems(getId, count)
}
