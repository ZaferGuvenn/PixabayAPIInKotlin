package com.lafimsize.mypixabaypicture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lafimsize.mypixabaypicture.model.ImageResponse
import com.lafimsize.mypixabaypicture.repo.PixabayImageRepository
import com.lafimsize.mypixabaypicture.util.Resource
import com.lafimsize.mypixabaypicture.util.SelectedLink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PixabayViewModel
@Inject
constructor(private val pixabayImageRepository: PixabayImageRepository):ViewModel() {


    private val images=MutableLiveData<Resource<ImageResponse>>()
    val imgs:LiveData<Resource<ImageResponse>>
        get()=images





    fun setSelectedImage(url:String){
        SelectedLink().selected(url)
    }


    fun searchForImage(query:String){

        if (query.isEmpty()) return


        images.value=Resource.loading(null)

        viewModelScope.launch {

            val response=pixabayImageRepository.searchImage(query)

            images.value=response

        }

    }



}