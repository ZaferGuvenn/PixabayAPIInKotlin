package com.lafimsize.mypixabaypicture.view

import android.media.MediaRouter.SimpleCallback
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.lafimsize.mypixabaypicture.R
import com.lafimsize.mypixabaypicture.adapter.PixabayRecyclerAdapter
import com.lafimsize.mypixabaypicture.databinding.FragmentPixabayBinding
import com.lafimsize.mypixabaypicture.util.Status
import com.lafimsize.mypixabaypicture.viewmodel.PixabayViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PixabayFragment
@Inject
constructor(val pixabayRecyclerAdapter: PixabayRecyclerAdapter) :Fragment(R.layout.fragment_pixabay) {

    private var fragmentBinding:FragmentPixabayBinding?=null

    private lateinit var viewModel:PixabayViewModel




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding=FragmentPixabayBinding.bind(view)
        fragmentBinding=binding

        viewModel=ViewModelProvider(this)[PixabayViewModel::class.java]


        binding.rvPixabayImages.adapter=pixabayRecyclerAdapter
        binding.rvPixabayImages.layoutManager=GridLayoutManager(context, 2)


        binding.etSearch.addTextChangedListener {

            it?.let {
                if (it.length<2) return@let
                else{
                    lifecycleScope.launch {
                        delay(2000)
                        viewModel.searchForImage(it.toString())
                    }
                }
            }

        }

        observeLiveData()


    }

    private fun observeLiveData(){

        viewModel.imgs.observe(viewLifecycleOwner){

            it?.let {

                when (it.status){

                    Status.Success->{


                        fragmentBinding?.progressBar?.visibility=View.GONE
                        val urls=it.data!!.hits.map {
                            it.previewURL
                        }
                        pixabayRecyclerAdapter.pixabayImageUrlList=urls
                        println(urls)
                    }

                    Status.Loading->{

                        fragmentBinding?.progressBar?.visibility=View.VISIBLE

                    }

                    Status.Error->{

                        fragmentBinding?.progressBar?.visibility=View.GONE
                        Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
                    }



                }


            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding=null
    }
}