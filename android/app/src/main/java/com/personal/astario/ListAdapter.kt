package com.personal.astario

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.personal.astario.databinding.ListRecyclerItemBinding

class ListAdapter(private val dataList : MutableList<ListData> = mutableListOf<ListData>()) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding : ListRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemView = binding.layListItem
        fun bind(listData: ListData, position: Int) {
            binding.textItemName.text = listData.name
            Glide.with(binding.imgListIcon.context).load(listData.imgUrl).into(binding.imgListIcon)
            if(position % 2 == 0)
                binding.layListItem.setBackgroundColor(Color.parseColor("#FAFAFA"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(dataList[position], position)
        holder.itemView.setOnClickListener {
            (holder.itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame_layout, StarInfoFragment(dataList, position)).addToBackStack(null).commit()
        }
    }
}