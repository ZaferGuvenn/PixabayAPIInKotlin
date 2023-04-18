package com.lafimsize.mypixabaypicture.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.lafimsize.mypixabaypicture.R
import com.lafimsize.mypixabaypicture.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject


@MediumTest
@HiltAndroidTest
class SavedPicturesFragmentTest {

    @get:Rule
    val hiltRule=HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Inject
    lateinit var fragmentFactory:PixabayAppFragmentFactory

    @Test
    fun testNavigationFromSavedPicturesToInsert(){

        val navController= Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<SavedPicturesFragment>(factory = fragmentFactory){
            Navigation.setViewNavController(requireView(),navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())

        Mockito.verify(navController)
            .navigate(SavedPicturesFragmentDirections.actionSavedPicturesFragmentToInsertFragment())


    }



}