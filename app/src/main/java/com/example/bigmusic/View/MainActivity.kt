package com.example.bigmusic.View

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.bigmusic.Data.Best.BestArtist.Artist
import com.example.bigmusic.Data.Best.BestTag.Tag
import com.example.bigmusic.Data.Best.BestTrack.Track
import com.example.bigmusic.R
import com.example.bigmusic.View.search.SearchActivity
import com.example.bigmusic.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FragmentAdapter
    private lateinit var vM : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val tabLayoutTextArray = arrayOf("베스트 뮤직","베스트 아티스트", "인기 태그")

        adapter = FragmentAdapter(supportFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter

        vM = ViewModelProvider(this).get(MainViewModel::class.java)

        TabLayoutMediator(binding.tapLayout, binding.viewPager){tap, position->
          tap.text = tabLayoutTextArray[position]
        }.attach()

        vM.bestArtist.value = intent.getSerializableExtra("bestArtist") as ArrayList<Artist>
        vM.bestArtistKr.value = intent.getSerializableExtra("bestArtistKr") as ArrayList<com.example.bigmusic.Data.Best.BestArtistKr.Artist>
        vM.bestTag.value = intent.getSerializableExtra("bestTag") as ArrayList<Tag>
        vM.bestTrack.value = intent.getSerializableExtra("bestTrack") as ArrayList<Track>
        vM.bestTrackKr.value = intent.getSerializableExtra("bestTrackKr") as ArrayList<com.example.bigmusic.Data.Best.BestTrackKr.Track>






        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search ->{
                startActivity(Intent(this, SearchActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }



}