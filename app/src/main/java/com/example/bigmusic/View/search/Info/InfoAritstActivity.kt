package com.example.bigmusic.View.search.Info

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.bigmusic.Data.Info.ArtistData.Tag.Tag
import com.example.bigmusic.Data.Search.SearchTrack.Track
import com.example.bigmusic.R
import com.example.bigmusic.View.search.Info.adapter.InfoTrackSimilarAdapter
import com.example.bigmusic.View.search.Info.adapter.InfoTrackTagAdapter
import com.example.bigmusic.databinding.ActivityInfoAritstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class InfoAritstActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInfoAritstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_aritst)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_info_aritst)

        if(intent.hasExtra("artist")){
            getArtistData()
        }
        else{
            getTrackData()
        }

        binding.toolbar2.title = ""

        binding.backBtn.setOnClickListener {
            finish()
        }


        setSupportActionBar(binding.toolbar2)
    }

    private fun getTrackData() {
        val data = intent.getSerializableExtra("track") as Track

        binding.textView2.text = "관련된 곡"
        binding.artistNameToolbarText.text = data.name
        binding.singerText.text = data.name
        Glide.with(this).load(data.image[2].text).into(binding.imageView)
        binding.likeListenerText.text = data.listeners
        binding.artistText.text = data.artist

        val api = Retrofit.Builder().baseUrl("https://ws.audioscrobbler.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrackDataService::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val a = api.getTrackSimilarData(data.artist, data.name).awaitResponse()
            val b = api.getTrackTagData(data.artist, data.name).awaitResponse()

            if(b.isSuccessful){
                val result = b.body()

                val tag = result!!.toptags.tag as ArrayList<com.example.bigmusic.Data.Info.TrackData.Tag.Tag>

                Log.d(TAG, "getTrackData: tag : $tag")

                withContext(Dispatchers.Main){
                    binding.tagRecycler.adapter = InfoTrackTagAdapter(tag)
                }

            }

            if(a.isSuccessful){
                val result = a.body()

                val track = result!!.similartracks.track as ArrayList<com.example.bigmusic.Data.Info.TrackData.Similare.Track>

                withContext(Dispatchers.Main){
                    binding.similarRecycler.adapter = InfoTrackSimilarAdapter(track)
                }
            }
        }
    }

    private fun getArtistData() {
        val api = Retrofit.Builder().baseUrl("https://ws.audioscrobbler.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrackDataService::class.java)
    }
}