package com.lafimsize.mypixabaypicture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.lafimsize.mypixabaypicture.databinding.SavedsRowBinding
import com.lafimsize.mypixabaypicture.roomdb.SavedPicture
import javax.inject.Inject

class SavedPicturesRecyclerAdapter
@Inject
constructor(val glide: RequestManager)
    :RecyclerView.Adapter<SavedPicturesRecyclerAdapter.SavedPicturesViewHolder>() {

    private lateinit var binding:SavedsRowBinding

    class SavedPicturesViewHolder(val binding: SavedsRowBinding):RecyclerView.ViewHolder(binding.root)


    private val diffUtilItemCallback=

    object : DiffUtil.ItemCallback<SavedPicture>() {

        override fun areItemsTheSame(oldItem: SavedPicture, newItem: SavedPicture): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: SavedPicture, newItem: SavedPicture)=
            oldItem==newItem
    }

    private val listDiffer=AsyncListDiffer(this,diffUtilItemCallback)

    var savedPictureList:List<SavedPicture>
        get()=listDiffer.currentList
        set(value)=listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedPicturesViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        binding= SavedsRowBinding.inflate(inflater)

        return SavedPicturesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedPicturesViewHolder, position: Int) {

        val savedPicture=savedPictureList[position]

        holder.binding.apply {

            glide.load(savedPicture.ImageUrl).into(this.SavedImage)
            tvPictureName.text="Name: ${savedPicture.ImageName}"
            tvCategory.text="Category: ${savedPicture.Category}"
            tvRank.text="Rank: ${savedPicture.Rank}"

        }

    }

    override fun getItemCount(): Int {
        return savedPictureList.size
    }
}