package com.lafimsize.mypixabaypicture.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lafimsize.mypixabaypicture.R
import com.lafimsize.mypixabaypicture.databinding.FragmentInsertBinding

class InsertFragment:Fragment(R.layout.fragment_insert) {

    private var fragmentBinding:FragmentInsertBinding?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding=FragmentInsertBinding.bind(view)
        fragmentBinding=binding

        //geriye gitme olayı
        val callback= object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)



    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding=null
    }
}