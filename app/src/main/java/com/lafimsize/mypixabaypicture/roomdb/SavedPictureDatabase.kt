package com.lafimsize.mypixabaypicture.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [SavedPicture::class], version = 1)
abstract class SavedPictureDatabase:RoomDatabase() {

    abstract fun savedPictureDao():SavedPictureDao


}