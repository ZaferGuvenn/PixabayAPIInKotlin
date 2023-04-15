package com.lafimsize.mypixabaypicture.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.lafimsize.mypixabaypicture.getOrAwaitValueTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class SavedPictureDaoTestWithHilt {

    @get:Rule
    val hiltRule=HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule=InstantTaskExecutorRule()

    @Inject
    @Named("getInAppModuleTest")
    lateinit var database: SavedPictureDatabase

    private lateinit var dao:SavedPictureDao

    @Before
    fun setup(){

        hiltRule.inject()

        dao=database.savedPictureDao()

    }

    @After
    fun tearDown(){

        database.close()
    }


    @Test
    fun insertImageWhenInputTrue(){

        runBlocking {
            val savedPicture=
                SavedPicture(1,"google.com","Image Name","Category",1903)

            dao.insertImage(savedPicture)

            val list=dao.getAllImages().getOrAwaitValueTest()

            assertThat(list).contains(savedPicture)

        }
    }

    @Test
    fun deletePreRegisteredImage(){

        runBlocking {
            val savedPicture=
                SavedPicture(1,"google.com","Image Name","Category",1903)

            dao.insertImage(savedPicture)
            dao.deleteImage(savedPicture)

            val list=dao.getAllImages().getOrAwaitValueTest()

            assertThat(list).doesNotContain(savedPicture)

        }

    }

    @Test
    fun deleteNotPreRegisteredImage(){
        val savedPicture=
            SavedPicture(1,"google.com","Image Name","Category",1903)

        runBlocking {

            dao.deleteImage(savedPicture)
            val list=dao.getAllImages().getOrAwaitValueTest()

            assertThat(list).doesNotContain(savedPicture)

        }
    }


}