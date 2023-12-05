package com.personal.astario

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.personal.astario.databinding.FragmentFortuneBinding


class FortuneFragment : Fragment() {
    private lateinit var binding: FragmentFortuneBinding
    private val dataList = mutableListOf<FortuneData>()
    private val adapter = FortuneAdapter(dataList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFortuneBinding.inflate(inflater, container, false)
        getFBContentData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerFortune.adapter = adapter
        binding.recyclerFortune.layoutManager = LinearLayoutManager(requireContext())
    }

    /*private val starList: ArrayList<String> = arrayListOf(
        "물병자리", "물고기자리", "양자리", "황소자리",
        "쌍둥이자리", "게자리", "사자자리", "처녀자리",
        "천칭자리", "전갈자리", "사수자리", "염소자리"
    )*/

    private fun getFBContentData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for(data in snapshot.children) {
                    val item = data.getValue(FortuneData::class.java)
                    Log.d("FortuneData", "item : $item")
                    dataList.add(item!!)
                }
                dataList.reverse()
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        FBRef.contentRef.addValueEventListener(postListener)
    }
}

class FBRef {
    companion object {
        private val database = Firebase.database
        val contentRef = database.reference
    }
}