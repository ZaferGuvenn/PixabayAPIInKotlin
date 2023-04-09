package com.lafimsize.mypixabaypicture.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lafimsize.mypixabaypicture.R
import com.lafimsize.mypixabaypicture.databinding.FragmentPixabayBinding

class PixabayFragment:Fragment(R.layout.fragment_pixabay) {

    private var fragmentBinding:FragmentPixabayBinding?=null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding=FragmentPixabayBinding.bind(view)
        fragmentBinding=binding


    }


    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding=null
    }
}