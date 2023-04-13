package com.lafimsize.mypixabaypicture.repo

import androidx.lifecycle.LiveData
import com.lafimsize.mypixabaypicture.api.RetrofitAPI
import com.lafimsize.mypixabaypicture.model.ImageResponse
import com.lafimsize.mypixabaypicture.roomdb.SavedPicture
import com.lafimsize.mypixabaypicture.roomdb.SavedPictureDao
import com.lafimsize.mypixabaypicture.util.Resource
import javax.inject.Inject

class PixabayImageRepository @Inject constructor(
    private val savedPictureDao:SavedPictureDao,
    private val retrofitAPI: RetrofitAPI) :IPixabayImagesRepository {
    override suspend fun insertImg(savedPicture: SavedPicture) {
        savedPictureDao.insertImage(savedPicture)
    }

    override suspend fun deleteImg(savedPicture: SavedPicture) {
        savedPictureDao.deleteImage(savedPicture)
    }

    override fun getSavedImages(): LiveData<List<SavedPicture>> {
        return savedPictureDao.getAllImages()
    }

    override suspend fun searchImage(query: String): Resource<ImageResponse> {

        return try {
            val response=retrofitAPI.searchImg(query = query)

            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?: Resource.error("Response hatası!", null)
            }else{
                Resource.error("İnternet bağlantınızı kontrol ediniz!", null)
            }
        }catch (e:java.lang.Exception){
            Resource.error("Bağlantı hatası!", null)
        }

    }
}