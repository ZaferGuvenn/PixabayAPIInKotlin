package com.lafimsize.mypixabaypicture.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.lafimsize.mypixabaypicture.launchFragmentInHiltContainer
import com.lafimsize.mypixabaypicture.repo.FakePixabayImageRepository
import com.lafimsize.mypixabaypicture.viewmodel.PixabayViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

import com.lafimsize.mypixabaypicture.R
import com.lafimsize.mypixabaypicture.adapter.PixabayRecyclerAdapter

import com.lafimsize.mypixabaypicture.getOrAwaitValueTest
import com.lafimsize.mypixabaypicture.util.SelectedLink
import com.lafimsize.mypixabaypicture.util.SelectedLink.Companion.linkStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class PixabayFragmentTest {

    @get:Rule
    val hiltRule=HiltAndroidRule(this)

    @get:Rule
    val iter=InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory:PixabayAppFragmentFactory

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun goBackAndSendImgWhenSelectImage(){

        val navController=Mockito.mock(NavController::class.java)
        val viewModelTest= PixabayViewModel(FakePixabayImageRepository())

        launchFragmentInHiltContainer<PixabayFragment>(factory = fragmentFactory){
            (this as PixabayFragment).viewModel=viewModelTest
            Navigation.setViewNavController(requireView(),navController)

            pixabayRecyclerAdapter.pixabayImageUrlList= listOf("google.com")

        }

        Espresso.onView(ViewMatchers.withId(R.id.rvPixabayImages)).perform(

            RecyclerViewActions
                .actionOnItemAtPosition<PixabayRecyclerAdapter.PixabayViewHolder>
                    (0, ViewActions.click())
        )

        Mockito.verify(navController).popBackStack()

        runBlocking {
            assertThat(linkStateFlow.collect()).isEqualTo("google.com")
        }


    }



}