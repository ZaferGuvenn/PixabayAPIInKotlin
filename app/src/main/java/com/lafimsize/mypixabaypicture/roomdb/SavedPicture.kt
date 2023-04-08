package com.lafimsize.mypixabaypicture.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class SavedPicture(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var ImageUrl:String,
    var ImageName:String,
    var Category:String,
    var Rank:Int


){

}