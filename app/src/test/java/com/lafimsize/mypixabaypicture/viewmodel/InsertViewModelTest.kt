package com.lafimsize.mypixabaypicture.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.lafimsize.mypixabaypicture.getOrAwaitValueTest
import com.lafimsize.mypixabaypicture.repo.FakePixabayImageRepository
import com.lafimsize.mypixabaypicture.util.Status
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class InsertViewModelTest {


    @get:Rule
    var instantTaskExecutorRule=InstantTaskExecutorRule()//her şeyi main threadda çalıştır kuralı

    private lateinit var viewModel:InsertViewModel

    @OptIn(DelicateCoroutinesApi::class)//test içinde main thread olmadığı için oluşturma işlemi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setup(){
        viewModel= InsertViewModel(FakePixabayImageRepository())
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `insert picture without rank returns error`(){
        runTest {
            viewModel.savingPicture("Test name","Test category","","")

            val value=viewModel.insertImgMsg.getOrAwaitValueTest()//live data veriye dönüşüyor.

            assertThat(value.status).isEqualTo(Status.Error)
        }
    }

    @Test
    fun `insert picture without category return error`(){
        runBlocking {
            launch(Dispatchers.Main){
                viewModel.savingPicture("Test name","","1903","")
                val value=viewModel.insertImgMsg.getOrAwaitValueTest()
                assertThat(value.status).isEqualTo(Status.Error)
            }
        }
    }

    @Test
    fun `insert picture without name return error`(){
        runBlocking {
            launch(Dispatchers.Main) {
                viewModel.savingPicture("","Test category","1903","")
                val value=viewModel.insertImgMsg.getOrAwaitValueTest()
                assertThat(value.status).isEqualTo(Status.Error)
            }
        }
    }

    @Test
    fun `insert picture rank is no int return error`(){
        runBlocking {
            launch(Dispatchers.Main) {
                viewModel.savingPicture("Test name","Test category","No-Int Values","")
                val value=viewModel.insertImgMsg.getOrAwaitValueTest()
                assertThat(value.status).isEqualTo(Status.Error)
            }
        }
    }

    @Test
    fun `insert picture when image url is not set return success`(){
        //method1
        runTest {
            viewModel.savingPicture("Test name","Test category","1903","")
            val value=viewModel.insertImgMsg.getOrAwaitValueTest()
            assertThat(value.status).isEqualTo(Status.Success)
        }
    }

    @Test
    fun `insert picture when all values is entered return success`(){
        //method2
        runBlocking {
            launch(Dispatchers.Main) {
                viewModel.savingPicture("Test name","Test category","1903","https://google.com")
                val value=viewModel.insertImgMsg.getOrAwaitValueTest()
                assertThat(value.status).isEqualTo(Status.Success)
            }
        }

    }
}