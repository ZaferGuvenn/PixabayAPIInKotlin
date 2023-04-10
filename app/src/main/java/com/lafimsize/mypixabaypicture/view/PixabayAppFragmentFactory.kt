package com.lafimsize.mypixabaypicture.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class PixabayAppFragmentFactory
@Inject
constructor( private val glide:RequestManager):FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){

            SavedPicturesFragment::class.java.name-> SavedPicturesFragment(glide)

            else -> super.instantiate(classLoader, className)



        }

    }
}