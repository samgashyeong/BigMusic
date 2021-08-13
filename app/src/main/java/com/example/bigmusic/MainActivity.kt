package com.example.bigmusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.example.bigmusic.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FragmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val tabLayoutTextArray = arrayOf("베스트 뮤직","베스트 아티스트", "베스트 뮤직-한국", "베스트 아티스트-한국", "인기 태그")

        adapter = FragmentAdapter(supportFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tapLayout, binding.viewPager){tap, position->
          tap.text = tabLayoutTextArray[position]
        }.attach()


    }
}