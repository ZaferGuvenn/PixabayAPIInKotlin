package com.lafimsize.mypixabaypicture.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.lafimsize.mypixabaypicture.adapter.PixabayRecyclerAdapter
import com.lafimsize.mypixabaypicture.adapter.SavedPicturesRecyclerAdapter
import com.lafimsize.mypixabaypicture.viewmodel.InsertViewModel
import com.lafimsize.mypixabaypicture.viewmodel.PixabayViewModel
import com.lafimsize.mypixabaypicture.viewmodel.SavedPicturesViewModel
import javax.inject.Inject


class PixabayAppFragmentFactory
@Inject
constructor(
    val glide:RequestManager,
    val savedPicturesRecyclerAdapter: SavedPicturesRecyclerAdapter,
    val pixabayRecyclerAdapter: PixabayRecyclerAdapter
):FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){

            SavedPicturesFragment::class.java.name-> SavedPicturesFragment(savedPicturesRecyclerAdapter)
            InsertFragment::class.java.name->InsertFragment(glide)
            PixabayFragment::class.java.name->PixabayFragment(pixabayRecyclerAdapter)

            else -> super.instantiate(classLoader, className)



        }

    }
}