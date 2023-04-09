package com.lafimsize.mypixabaypicture.di

import android.content.Context
import androidx.room.Room
import com.lafimsize.mypixabaypicture.api.RetrofitAPI
import com.lafimsize.mypixabaypicture.roomdb.SavedPictureDatabase
import com.lafimsize.mypixabaypicture.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun injectRoomDb(@ActivityContext context: Context) =
        Room.databaseBuilder(context,SavedPictureDatabase::class.java, "SavedPicturesDb").build()

    @Singleton
    @Provides
    fun injectRoomDao(database:SavedPictureDatabase)=database.savedPictureDao()

    @Singleton
    @Provides
    fun injectRetrofitAPI()= Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(RetrofitAPI::class.java)
}