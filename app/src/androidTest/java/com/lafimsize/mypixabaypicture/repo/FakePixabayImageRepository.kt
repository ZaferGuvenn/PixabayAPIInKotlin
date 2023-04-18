package com.lafimsize.mypixabaypicture.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lafimsize.mypixabaypicture.model.ImageResponse
import com.lafimsize.mypixabaypicture.roomdb.SavedPicture
import com.lafimsize.mypixabaypicture.util.Resource

class FakePixabayImageRepository:IPixabayImagesRepository{

    private val list= mutableListOf<SavedPicture>()
    private val liveDataList=MutableLiveData<List<SavedPicture>>(list)


    override suspend fun insertImg(savedPicture: SavedPicture) {
        list.add(savedPicture)
        updateLiveDataList()
    }

    override suspend fun deleteImg(savedPicture: SavedPicture) {
        list.remove(savedPicture)
        updateLiveDataList()
    }

    override fun getSavedImages(): LiveData<List<SavedPicture>> {
        return liveDataList
    }

    override suspend fun searchImage(query: String): Resource<ImageResponse> {

        return Resource.success(ImageResponse(0,0, listOf()))

    }

    private fun updateLiveDataList(){liveDataList.value=list}

}