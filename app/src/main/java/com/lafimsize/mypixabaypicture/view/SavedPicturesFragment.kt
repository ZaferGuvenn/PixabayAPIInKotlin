package com.lafimsize.mypixabaypicture.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lafimsize.mypixabaypicture.R
import com.lafimsize.mypixabaypicture.databinding.FragmentSavedPicturesBinding

class SavedPicturesFragment:Fragment(R.layout.fragment_saved_pictures) {

    private var fragmentBinding:FragmentSavedPicturesBinding?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding=FragmentSavedPicturesBinding.bind(view)

        fragmentBinding=binding

        val action=SavedPicturesFragmentDirections.actionSavedPicturesFragmentToInsertFragment()

        binding.fab.setOnClickListener {
            findNavController().navigate(action)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding=null
    }
}