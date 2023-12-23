package com.personal.astario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.personal.astario.databinding.FragmentStarInfoBinding

class StarInfoFragment(dataList : List<ListData>, position: Int) : Fragment() {
    private lateinit var binding : FragmentStarInfoBinding
    private val data = dataList[position]
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStarInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(binding.imgInfo.context).load(data.imgUrl).into(binding.imgInfo)
        binding.textInfoName.text = data.name
        binding.textInfoStoryContent.text = data.story
        binding.textInfoCharacterContent.text = data.introduce
        binding.textInfoTitle.text = data.name

        binding.imgInfoBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}