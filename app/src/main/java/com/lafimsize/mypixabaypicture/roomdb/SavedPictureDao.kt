package com.lafimsize.mypixabaypicture.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SavedPictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(savedPicture:SavedPicture)

    @Delete
    suspend fun deleteImage(savedPicture: SavedPicture)

    @Query("SELECT * FROM SavedPicture")
    fun getAllImages():LiveData<List<SavedPicture>>
}