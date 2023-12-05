package com.personal.astario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.personal.astario.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.spinnerHome.adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_text,
            resources.getStringArray(R.array.itemList)
        )
        binding.spinnerHome.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position : Int,
                id : Long
            ) {
                when(position) {
                    0 -> binding.imgHome.setImageResource(R.drawable.spring)
                    1 -> binding.imgHome.setImageResource(R.drawable.summer)
                    2 -> binding.imgHome.setImageResource(R.drawable.autumn)
                    3 -> binding.imgHome.setImageResource(R.drawable.winter)
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }
}