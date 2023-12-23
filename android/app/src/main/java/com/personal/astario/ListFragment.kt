package com.personal.astario

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.personal.astario.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding : FragmentListBinding
    private val dataList = mutableListOf<ListData>()
    private val adapter = ListAdapter(dataList)
    private var uid : String? = null
    private var season = "spring"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        uid = FirebaseAuth.getInstance().currentUser?.uid
        readData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerList.adapter = adapter
        binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())

        binding.layoutListTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0->season = "spring"
                    1->season = "summer"
                    2->season = "fall"
                    3->season = "winter"
                    4->season = "ecliptic"
                }
                readData()
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun readData() {
        val fireStore : FirebaseFirestore = FirebaseFirestore.getInstance()
        fireStore.collection(season).get().addOnSuccessListener { result ->
            dataList.clear()
            for(document in result) {
                val item = ListData(
                    document.getString("name") ?: "",
                    document.getString("imgUrl") ?: "",
                    document.getString("introduce") ?: "",
                    document.getString("story") ?: ""
                )
                dataList.add(item)
            }
            adapter.notifyDataSetChanged()
        }
            .addOnFailureListener { exception ->
                Log.e("firebase", "List Error $exception")
            }
    }
}