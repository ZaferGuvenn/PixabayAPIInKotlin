package com.lafimsize.mypixabaypicture.model

import com.google.gson.annotations.SerializedName

data class ImageResult(

    val downloads:Int,
    val previewUrl:String,

    @SerializedName("user_id")
    val userId:Int
)