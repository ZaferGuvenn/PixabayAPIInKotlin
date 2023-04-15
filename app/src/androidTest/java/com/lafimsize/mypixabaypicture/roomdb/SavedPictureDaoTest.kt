package com.lafimsize.mypixabaypicture.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.lafimsize.mypixabaypicture.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class SavedPictureDaoTest {

    @get:Rule
    val instantTaskExecutorRule=InstantTaskExecutorRule()

    private lateinit var database: SavedPictureDatabase
    private lateinit var dao:SavedPictureDao


    @Before
    fun setup(){

        database= Room
            .inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),SavedPictureDatabase::class.java)
            .allowMainThreadQueries().build()

        dao=database.savedPictureDao()

    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertImage_validInput_savedPictureInserted(){

        runBlocking {
            val savedPictureObject=
                SavedPicture(1,"google.com","Image Name","Category",1903)

            dao.insertImage(savedPictureObject)

            val getSavedPictureList=dao.getAllImages().getOrAwaitValueTest()

            assertThat(getSavedPictureList).contains(savedPictureObject)
        }

    }

    @Test
    fun deletePreRegisteredImage(){

        runBlocking {

            val savedPictureObject=
                SavedPicture(1,"google.com","Image Name","Category",1903)

            dao.insertImage(savedPictureObject)
            dao.deleteImage(savedPictureObject)

            val list=dao.getAllImages().getOrAwaitValueTest()

            assertThat(list).doesNotContain(savedPictureObject)

        }

    }

    @Test
    fun deleteNotPreRegisteredImage(){

        runBlocking {

            val savedPictureObject=
                SavedPicture(1,"google.com","Image Name","Category",1903)

            dao.deleteImage(savedPictureObject)

            val list=dao.getAllImages().getOrAwaitValueTest()

            assertThat(list).doesNotContain(savedPictureObject)

        }

    }




}