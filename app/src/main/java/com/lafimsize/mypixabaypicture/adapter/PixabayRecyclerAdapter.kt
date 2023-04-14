package com.lafimsize.mypixabaypicture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.lafimsize.mypixabaypicture.databinding.PixabayRowBinding
import javax.inject.Inject

class PixabayRecyclerAdapter
@Inject
constructor(val glide:RequestManager):RecyclerView.Adapter<PixabayRecyclerAdapter.PixabayViewHolder>() {

    private lateinit var binding: PixabayRowBinding
    class PixabayViewHolder(val binding: PixabayRowBinding):RecyclerView.ViewHolder(binding.root)

    private val diffUtilItemCallback= object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }
    }


    private val listDiffer=AsyncListDiffer(this,diffUtilItemCallback)

    var pixabayImageUrlList:List<String>
        get()=listDiffer.currentList
        set(value)=listDiffer.submitList(value)





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayViewHolder {
        val inflater=LayoutInflater.from(parent.context)

        binding=PixabayRowBinding.inflate(inflater)

        return PixabayViewHolder(binding)
    }

    private var onItemClickListener:((String)->Unit)?=null

    fun setOnItemClickListener(listener:(String)->Unit){
        onItemClickListener=listener
    }


    override fun onBindViewHolder(holder: PixabayViewHolder, position: Int) {
        val imageUrl=pixabayImageUrlList[position]

        holder.binding.apply {



            glide.load(imageUrl).into(this.ivPixabay)

        }

        holder.itemView.setOnClickListener {
            println("deneme")
            onItemClickListener?.let {
                it(imageUrl)
            }

        }
    }

    override fun getItemCount(): Int {
        return pixabayImageUrlList.size
    }
}