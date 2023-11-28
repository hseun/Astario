package com.greenonion.astario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greenonion.astario.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFrag(0)
        binding.mainBottomNavigation.selectedItemId = R.id.menu_home

        binding.mainBottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_home -> setFrag(0)
                R.id.menu_list -> setFrag(1)
                R.id.menu_fortune -> setFrag(2)
                else -> false
            }
        }
    }

    private fun setFrag(fragNum : Int) : Boolean {
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum) {
            0 -> ft.replace(R.id.main_frame_layout, HomeFragment()).commit()
            1 -> ft.replace(R.id.main_frame_layout, ListFragment()).commit()
            2 -> ft.replace(R.id.main_frame_layout, FortuneFragment()).commit()
        }
        return true
    }
}