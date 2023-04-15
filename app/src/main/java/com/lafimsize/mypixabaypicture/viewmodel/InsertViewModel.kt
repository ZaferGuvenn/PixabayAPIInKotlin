package com.lafimsize.mypixabaypicture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lafimsize.mypixabaypicture.repo.IPixabayImagesRepository
import com.lafimsize.mypixabaypicture.roomdb.SavedPicture
import com.lafimsize.mypixabaypicture.repo.PixabayImageRepository
import com.lafimsize.mypixabaypicture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertViewModel
@Inject
constructor(private val pixabayImageRepository: IPixabayImagesRepository):ViewModel() {


    private var insertImageMessage=MutableLiveData<Resource<SavedPicture>>()
    val insertImgMsg: LiveData<Resource<SavedPicture>>
        get()=insertImageMessage

    fun resetInsertImgMsg(){
        insertImageMessage=MutableLiveData<Resource<SavedPicture>>()
    }

    fun insertSavedImage(savedPicture: SavedPicture)=
        viewModelScope.launch {

            pixabayImageRepository.insertImg(savedPicture)

        }


    fun savingPicture(imgName:String,imgCategory:String,imgRank:String,imgUrl:String?){

        if (imgName.isEmpty() || imgCategory.isEmpty() || imgRank.isEmpty()){

            insertImageMessage.value=Resource.error("Lütfen bilgileri girin!",null)

            return
        }

        val imgRankInt=try {
            imgRank.toInt()
        }catch (e:java.lang.Exception){
            insertImageMessage.value=Resource.error("Lütfen rank değerini sayı olarak girin!",null)
            return
        }

        val imgInfo=SavedPicture(null,imgUrl?:"",imgName,imgCategory,imgRankInt)

        insertSavedImage(imgInfo)

        insertImageMessage.value=Resource.success(imgInfo)

    }


}