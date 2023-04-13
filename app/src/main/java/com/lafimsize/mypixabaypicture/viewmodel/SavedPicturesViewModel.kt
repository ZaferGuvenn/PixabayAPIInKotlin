package com.lafimsize.mypixabaypicture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lafimsize.mypixabaypicture.repo.IPixabayImagesRepository
import com.lafimsize.mypixabaypicture.roomdb.SavedPicture
import com.lafimsize.mypixabaypicture.repo.PixabayImageRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SavedPicturesViewModel @Inject constructor(
    private val repository: IPixabayImagesRepository
    ) :ViewModel() {

    val savedImagesList=repository.getSavedImages()


    fun deleteSavedImage(savedPicture: SavedPicture)=
        viewModelScope.launch {
            repository.deleteImg(savedPicture)
        }


}