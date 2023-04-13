package com.lafimsize.mypixabaypicture.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lafimsize.mypixabaypicture.R
import com.lafimsize.mypixabaypicture.adapter.SavedPicturesRecyclerAdapter
import com.lafimsize.mypixabaypicture.databinding.FragmentSavedPicturesBinding
import com.lafimsize.mypixabaypicture.viewmodel.SavedPicturesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SavedPicturesFragment
@Inject
constructor(val savedPicturesRecyclerAdapter: SavedPicturesRecyclerAdapter

):Fragment(R.layout.fragment_saved_pictures) {

    private var fragmentBinding:FragmentSavedPicturesBinding?=null
    private lateinit var viewModel:SavedPicturesViewModel

    private val swipeCallback=object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder)=true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val swipedImage=savedPicturesRecyclerAdapter.savedPictureList[viewHolder.layoutPosition]
            viewModel.deleteSavedImage(swipedImage)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding=FragmentSavedPicturesBinding.bind(view)

        fragmentBinding=binding
        viewModel= ViewModelProvider(requireActivity())[SavedPicturesViewModel::class.java]

        binding.rvSaveds.layoutManager=LinearLayoutManager(context)
        binding.rvSaveds.adapter=savedPicturesRecyclerAdapter
        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.rvSaveds)

        observeAllData()


        binding.fab.setOnClickListener {
            val action=SavedPicturesFragmentDirections.actionSavedPicturesFragmentToInsertFragment()
            findNavController().navigate(action)
        }

    }

    private fun observeAllData(){
        viewModel.savedImagesList.observe(viewLifecycleOwner){
            savedPicturesRecyclerAdapter.savedPictureList=it
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding=null
    }
}