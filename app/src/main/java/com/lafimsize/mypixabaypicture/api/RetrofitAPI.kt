package com.lafimsize.mypixabaypicture.api

import com.lafimsize.mypixabaypicture.model.ImageResponse
import com.lafimsize.mypixabaypicture.util.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("/api/")
    suspend fun searchImg(

        @Query("key") key: String = API_KEY,

        @Query("q") query:String

        ):Response<ImageResponse>
}