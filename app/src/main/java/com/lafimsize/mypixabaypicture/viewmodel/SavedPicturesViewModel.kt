package com.lafimsize.mypixabaypicture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lafimsize.mypixabaypicture.roomdb.SavedPicture
import com.lafimsize.mypixabaypicture.util.PixabayImageRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedPicturesViewModel

@Inject
constructor(private val pixabayImageRepository: PixabayImageRepository)
    :ViewModel() {

    val savedImagesList=pixabayImageRepository.getSavedImages()


    fun deleteSavedImage(savedPicture: SavedPicture)=
        viewModelScope.launch {

            pixabayImageRepository.deleteImg(savedPicture)

        }


}