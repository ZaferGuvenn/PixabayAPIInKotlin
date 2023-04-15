package com.lafimsize.mypixabaypicture.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lafimsize.mypixabaypicture.model.ImageResponse
import com.lafimsize.mypixabaypicture.roomdb.SavedPicture
import com.lafimsize.mypixabaypicture.util.Resource

class FakePixabayImageRepository:IPixabayImagesRepository {

    private val savedPictures= mutableListOf<SavedPicture>()
    private val savedPicturesLiveData=MutableLiveData<List<SavedPicture>>(savedPictures)


    override suspend fun insertImg(savedPicture: SavedPicture) {
        savedPictures.add(savedPicture)
        refreshData()
    }

    override suspend fun deleteImg(savedPicture: SavedPicture) {
        savedPictures.remove(savedPicture)
        refreshData()
    }

    override fun getSavedImages(): LiveData<List<SavedPicture>> {
        return savedPicturesLiveData
    }

    override suspend fun searchImage(query: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(0,0, listOf()))//pass etmesi için, direk doldurduk.
    }

    private fun refreshData(){savedPicturesLiveData.value=savedPictures}
}