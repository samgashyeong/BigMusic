package com.example.bigmusic.View.search.Info.tag

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.bigmusic.Data.Info.Tag.Artist.Artist
import com.example.bigmusic.Data.Info.Tag.Track.Track
import com.example.bigmusic.R
import com.example.bigmusic.View.ReturnK
import com.example.bigmusic.View.SecretKey
import com.example.bigmusic.View.search.Info.ImageNaverService
import com.example.bigmusic.View.search.Info.tag.adapter.TagArtistAdapter
import com.example.bigmusic.View.search.Info.tag.adapter.TagTrackAdapter
import com.example.bigmusic.databinding.ActivityInfoTagBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class InfoTagActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInfoTagBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_info_tag)
        
        val tagName = intent.getStringExtra("tagName")

        binding.likeListenerText.text = ReturnK(intent.getStringExtra("tagListener")!!.toLong()).formatNumber(intent.getStringExtra("tagListener")!!.toLong())
        binding.tagText.text = "#$tagName"

        binding.backBtn.setOnClickListener {
            finish()
        }


        Log.d(TAG, "onCreate: tagName = $tagName")
        getTagArtistData(tagName)
        getTagImage("$tagName music kind")
    }

    private fun getTagImage(tagName: String?) {
        val api = Retrofit.Builder().baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageNaverService::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val a =
                tagName?.let { api.getImageData(it, 1, SecretKey().naverId, SecretKey().naverPw).awaitResponse() }

            Log.d(TAG, "getImageData: a : $a")
            if(a!!.isSuccessful){
                val result = a!!.body()

                withContext(Dispatchers.Main){
                    Glide.with(this@InfoTagActivity)
                        .load(result!!.items[0].thumbnail)
                        .error(R.drawable.icon)
                        .placeholder(R.drawable.icon)
                        .into(binding.tagImage)
                }
                Log.d(TAG, "getImageData: resultNaver = $result")
            }
        }
    }

    private fun getTagTrackData(tagName: String?) {
        val api: TagService = Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TagService::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val check = api.getTopTrackData(tagName!!).awaitResponse()

            if(check.isSuccessful){
                val result = check.body()

                withContext(Dispatchers.Main){
                    val list = result!!.tracks.track as ArrayList<Track>
                    binding.listView.visibility = View.VISIBLE
                    binding.pgb.visibility = View.INVISIBLE
                    binding.trackRecycler.adapter = TagTrackAdapter(list)
                }
                Log.d(TAG, "getTagTrackData: tagTrack : $result")
            }

        }
    }

    private fun getTagArtistData(tagName: String?) {
        val api: TagService = Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TagService::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val check = api.getTopArtistData(tagName!!).awaitResponse()

            Log.d(TAG, "getTagArtistData: check : $check")

            if(check.isSuccessful){
                val result = check.body()
                getTagTrackData(tagName)
                withContext(Dispatchers.Main){
                    val list = result!!.topartists.artist as ArrayList<Artist>

                    binding.artistRecycler.adapter = TagArtistAdapter(list)
                }
                Log.d(TAG, "getTagTrackData: tagArtist : $result")
            }

        }
    }
}