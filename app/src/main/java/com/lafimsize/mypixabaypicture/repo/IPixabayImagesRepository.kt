package com.lafimsize.mypixabaypicture.repo

import androidx.lifecycle.LiveData
import com.lafimsize.mypixabaypicture.model.ImageResponse
import com.lafimsize.mypixabaypicture.roomdb.SavedPicture
import com.lafimsize.mypixabaypicture.util.Resource

interface IPixabayImagesRepository {

    suspend fun insertImg(savedPicture:SavedPicture)

    suspend fun deleteImg(savedPicture: SavedPicture)

    fun getSavedImages():LiveData<List<SavedPicture>>

    suspend fun searchImage(query:String):Resource<ImageResponse>
}