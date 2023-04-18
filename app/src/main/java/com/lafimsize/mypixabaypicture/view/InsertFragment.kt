package com.lafimsize.mypixabaypicture.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.lafimsize.mypixabaypicture.R
import com.lafimsize.mypixabaypicture.databinding.FragmentInsertBinding
import com.lafimsize.mypixabaypicture.util.SelectedLink
import com.lafimsize.mypixabaypicture.util.Status
import com.lafimsize.mypixabaypicture.viewmodel.InsertViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InsertFragment
@Inject
constructor(val glide: RequestManager) :Fragment(R.layout.fragment_insert) {

    private var fragmentBinding:FragmentInsertBinding?=null
    lateinit var viewModel:InsertViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding=FragmentInsertBinding.bind(view)
        fragmentBinding=binding
        viewModel=ViewModelProvider(requireActivity())[InsertViewModel::class.java]

        //geriye gitme olayı
        val callback= object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)


        binding.btnSave.setOnClickListener {
            viewModel.savingPicture(
                binding.etImageName.text.toString(),
                binding.etCategory.text.toString(),
                binding.etRank.text.toString(),
                SelectedLink.linkStateFlow.value
            )
        }


        binding.ivSelectImage.setOnClickListener {
            val action=InsertFragmentDirections.actionInsertFragmentToPixabayFragment()
            findNavController().navigate(action)
        }

        observeLiveData()

    }

    override fun onResume() {
        super.onResume()
        fragmentBinding?.let {
            if(SelectedLink.linkStateFlow.value.isEmpty()){
                return
            }
            glide.load(SelectedLink.linkStateFlow.value).into(it.ivSelectImage)
        }
    }

    private fun observeLiveData(){
        viewModel.insertImgMsg.observe(viewLifecycleOwner){

            when(it.status){

                Status.Success-> {
                    findNavController().popBackStack()
                    Toast.makeText(context,"Resim başarıyla kaydedildi!",Toast.LENGTH_SHORT).show()
                    viewModel.resetInsertImgMsg()

                }
                Status.Error->{
                    Toast.makeText(context,it.message?:"",Toast.LENGTH_SHORT).show()
                }

                Status.Loading->{

                }


            }


        }

    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding=null
    }
}