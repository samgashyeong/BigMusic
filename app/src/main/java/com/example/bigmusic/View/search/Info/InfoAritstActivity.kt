package com.example.bigmusic.View.search.Info

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.bigmusic.Data.Info.ArtistData.Tag.Tag
import com.example.bigmusic.Data.Search.SearchArtist.Artist
import com.example.bigmusic.Data.Search.SearchTrack.Track
import com.example.bigmusic.R
import com.example.bigmusic.View.ReturnK
import com.example.bigmusic.View.SecretKey
import com.example.bigmusic.View.search.Info.adapter.artist.InfoArtistSimilarAdapter
import com.example.bigmusic.View.search.Info.adapter.artist.InfoArtistTagAdapter
import com.example.bigmusic.View.search.Info.adapter.track.InfoTrackSimilarAdapter
import com.example.bigmusic.View.search.Info.adapter.track.InfoTrackTagAdapter
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

    private fun getImageData(s: String) {
        val api = Retrofit.Builder().baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageNaverService::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val a = api.getImageData(s, 1,SecretKey().naverId, SecretKey().naverPw).awaitResponse()

            Log.d(TAG, "getImageData: a : $a")
            if(a.isSuccessful){
                val result = a.body()


                withContext(Dispatchers.Main){
                    Glide.with(this@InfoAritstActivity)
                        .load(result!!.items[0].thumbnail)
                        .error(R.drawable.icon)
                        .placeholder(R.drawable.icon)
                        .into(binding.imageView)
                }
                Log.d(TAG, "getImageData: resultNaver = $result")
            }
        }
    }

    private fun getTrackData() {
        val data = intent.getSerializableExtra("track") as Track

        getImageData("${data.artist}-${data.name}")
        binding.textView2.text = "관련된 곡"
        binding.artistNameToolbarText.text = data.name
        binding.singerText.text = data.name
        Glide.with(this).load(data.image[2].text).into(binding.imageView)
        val playCount = ReturnK(data.listeners.toLong()).formatNumber(data.listeners.toLong())
        binding.likeListenerText.text = playCount
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
        val data = intent.getSerializableExtra("artist") as Artist

        getImageData(data.name)
        val api = Retrofit.Builder().baseUrl("https://ws.audioscrobbler.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArtistDataService::class.java)

        binding.textView2.text = "관련된 아티스트"
        binding.artistNameToolbarText.text = data.name
        binding.singerText.text = data.name
        Glide.with(this).load(data.image[2].text).into(binding.imageView)
        val playCount = ReturnK(data.listeners.toLong()).formatNumber(data.listeners.toLong())
        binding.likeListenerText.text = playCount
        binding.artistText.visibility = View.GONE


        GlobalScope.launch(Dispatchers.IO) {
            val a = api.getArtistSimilar(data.name).awaitResponse()
            val b = api.getArtistTagData(data.name).awaitResponse()

            Log.d(TAG, "getArtistData: b : $b")

            if(a.isSuccessful){
                val result = a.body()

                val similarAritstList = result!!.similarartists.artist as ArrayList<com.example.bigmusic.Data.Info.ArtistData.Similar.Artist>

                withContext(Dispatchers.Main){
                    binding.similarRecycler.adapter = InfoArtistSimilarAdapter(similarAritstList)
                }
            }

            if(b.isSuccessful){
                val  result = b.body()

                val tagArtistList = result!!.toptags.tag as ArrayList<Tag>

                Log.d(TAG, "getArtistData: tagArtistList : $tagArtistList")

                withContext(Dispatchers.Main){
                    binding.tagRecycler.adapter = InfoArtistTagAdapter(tagArtistList)
                }
            }
        }
    }
}