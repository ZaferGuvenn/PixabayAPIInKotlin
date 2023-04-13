package com.lafimsize.mypixabaypicture.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lafimsize.mypixabaypicture.R
import com.lafimsize.mypixabaypicture.api.RetrofitAPI
import com.lafimsize.mypixabaypicture.repo.IPixabayImagesRepository
import com.lafimsize.mypixabaypicture.roomdb.SavedPictureDao
import com.lafimsize.mypixabaypicture.roomdb.SavedPictureDatabase
import com.lafimsize.mypixabaypicture.repo.PixabayImageRepository
import com.lafimsize.mypixabaypicture.util.Util.BASE_URL
import com.lafimsize.mypixabaypicture.viewmodel.InsertViewModel
import com.lafimsize.mypixabaypicture.viewmodel.SavedPicturesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun injectRoomDb(@ApplicationContext context: Context) =
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


    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context)=Glide
        .with(context)
        .setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.mipmap.loading)
                .error(R.drawable.ic_launcher_foreground)
        )


    @Singleton
    @Provides
    fun injectNormalRepo(dao:SavedPictureDao,api:RetrofitAPI)=
        PixabayImageRepository(dao,api) as IPixabayImagesRepository



}