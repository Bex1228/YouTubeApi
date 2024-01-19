package com.example.youtubeapi.data.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtubeapi.data.service.Resource
import kotlinx.coroutines.Dispatchers

abstract class BaseRepository {

     fun <T>apiRequest(request : suspend () -> T): LiveData<Resource<T>> = liveData(Dispatchers.IO){
         emit(Resource.Loading())
         try {
             val response = request()
             if (response!= null){
                 emit(Resource.Success(response))
             }
         }catch (ex : Exception){
             emit(Resource.Error(ex.localizedMessage?: ex.message.toString()))
         }
     }
 }