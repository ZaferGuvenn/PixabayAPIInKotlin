package com.lafimsize.mypixabaypicture.di

import android.content.Context
import androidx.room.Room
import com.lafimsize.mypixabaypicture.roomdb.SavedPictureDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModuleTest {

    @Provides
    @Singleton
    @Named("getInAppModuleTest")
    fun injectRoomTest(@ApplicationContext context: Context)=
        Room.inMemoryDatabaseBuilder(context,SavedPictureDatabase::class.java)
            .allowMainThreadQueries().build()
}