package com.lafimsize.mypixabaypicture.util

import kotlinx.coroutines.flow.*


object Util {

    const val API_KEY="34185700-6f01d0679fb4e29b86d08e422"
    const val BASE_URL="https://pixabay.com"
}

class SelectedLink(){
    companion object{

        val linkStateFlow= MutableStateFlow("")

    }
    fun selected(link:String){
        linkStateFlow.value=link
    }

}

