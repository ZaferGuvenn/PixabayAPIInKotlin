package com.lafimsize.mypixabaypicture.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.lafimsize.mypixabaypicture.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

import com.lafimsize.mypixabaypicture.R
import com.lafimsize.mypixabaypicture.getOrAwaitValueTest
import com.lafimsize.mypixabaypicture.repo.FakePixabayImageRepository
import com.lafimsize.mypixabaypicture.roomdb.SavedPicture
import com.lafimsize.mypixabaypicture.util.Status
import com.lafimsize.mypixabaypicture.viewmodel.InsertViewModel
import com.lafimsize.mypixabaypicture.viewmodel.SavedPicturesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class InsertFragmenTest {

    @get:Rule
    val instantTaskExecutorRule=InstantTaskExecutorRule()

    @get:Rule
    val hiltRule=HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: PixabayAppFragmentFactory

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun navigationInsertFragmentToPixabayFragment(){

        val navController=Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<InsertFragment>(factory = fragmentFactory){

            Navigation.setViewNavController(requireView(),navController)

            //findNavController().navigate(InsertFragmentDirections.actionInsertFragmentToPixabayFragment())


        }

        Espresso.onView(ViewMatchers.withId(R.id.ivSelectImage)).perform(ViewActions.click())

        Mockito.verify(navController)
            .navigate(InsertFragmentDirections.actionInsertFragmentToPixabayFragment())



    }


    @Test
    fun goBackFragmentOnBackPressed(){

        val navController=Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<InsertFragment>(factory = fragmentFactory){

            Navigation.setViewNavController(requireView(),navController)

        }

        Espresso.pressBack()

        Mockito.verify(navController).popBackStack()

    }

    @Test
    fun saveImageWhenSaveButtonClicked(){

        val viewModelTest=InsertViewModel(FakePixabayImageRepository())


        launchFragmentInHiltContainer<InsertFragment>(factory = fragmentFactory){

            (this as InsertFragment).viewModel=viewModelTest


        }

        Espresso.onView(ViewMatchers.withId(R.id.etImageName))
            .perform(ViewActions.replaceText("IName"))
        Espresso.onView(ViewMatchers.withId(R.id.etCategory))
            .perform(ViewActions.replaceText("Category"))
        Espresso.onView(ViewMatchers.withId(R.id.etRank))
            .perform(ViewActions.replaceText("1903"))
        Espresso.onView(ViewMatchers.withId(R.id.btnSave))
            .perform(ViewActions.click())

        val savedPictureEspresso=
            SavedPicture(null,"","IName","Category",1903)


        val img=viewModelTest.insertImgMsg.getOrAwaitValueTest()

        assertThat(img.data).isEqualTo(savedPictureEspresso)

    }


}