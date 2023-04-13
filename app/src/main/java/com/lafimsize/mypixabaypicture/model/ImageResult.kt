package com.lafimsize.mypixabaypicture.model

import com.google.gson.annotations.SerializedName

data class ImageResult(

    val downloads:Int,
    val previewURL:String,

    @SerializedName("user_id")
    val userId:Int
)