package com.example.youtubeapi.data.repository



import androidx.lifecycle.LiveData
import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.data.base.BaseRepository
import com.example.youtubeapi.data.model.BaseResponse
import com.example.youtubeapi.data.model.PlayListItemsModel
import com.example.youtubeapi.data.service.Resource
import com.example.youtubeapi.data.service.YouTubeApiService
import com.example.youtubeapi.utils.YouTubeKeys

class YouTubeRepository(
    private val service: YouTubeApiService
):BaseRepository() {
    fun getPlaylists(): LiveData<Resource<List<BaseResponse.Item>>> = apiRequest {
        service.getPlaylists(
            BuildConfig.API_KEY,
            YouTubeKeys.CHANNEL_ID,
            YouTubeKeys.PART,
            YouTubeKeys.MAX_RESULTS,
        ).items
    }

    fun getPlaylistItems(getId:String, count: Int):LiveData<Resource<List<PlayListItemsModel.Item>>> = apiRequest {
        service.getPlaylistItems(
            BuildConfig.API_KEY,
            getId,
            count,
            YouTubeKeys.PART,
        ).items
    }
}