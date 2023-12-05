package com.personal.astario

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.personal.astario.databinding.FortuneRecyclerItemBinding

class FortuneAdapter(val dataList: MutableList<FortuneData> = mutableListOf<FortuneData>()) : RecyclerView.Adapter<FortuneAdapter.FortuneViewHolder>() {
    inner class FortuneViewHolder(private val binding : FortuneRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fortuneData : FortuneData) {
            binding.textFortuneName.text = fortuneData.name
            binding.textFortuneDuring.text = fortuneData.during
            binding.textFortuneContent.text = fortuneData.content
            Glide.with(binding.imgFortuneIcon.context).load(fortuneData.iconUrl).into(binding.imgFortuneIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FortuneViewHolder {
        val binding = FortuneRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FortuneViewHolder(binding)
    }
    override fun getItemCount(): Int = dataList.size
    override fun onBindViewHolder(holder: FortuneViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}