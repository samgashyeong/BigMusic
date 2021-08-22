package com.example.bigmusic.View.search

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.example.bigmusic.Data.Search.SearchArtist.Artist
import com.example.bigmusic.Data.Search.SearchTrack.Track
import com.example.bigmusic.R
import com.example.bigmusic.View.search.Info.InfoAritstActivity
import com.example.bigmusic.View.search.adapter.ArtistAdapter
import com.example.bigmusic.View.search.adapter.TrackAdapter
import com.example.bigmusic.databinding.ActivitySearchBinding
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private var returnTrackData : ArrayList<Track> = ArrayList()
    private var returnArtistData : ArrayList<Artist> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.editText.addTextChangedListener {
            if(binding.editText.text.toString().count() > 0){
                getSearchArtistData(binding.editText.text.toString())
                getSearchTrackData(binding.editText.text.toString())
            }
        }

        val artistAdapter = ArtistAdapter(returnArtistData){
            startActivity(Intent(applicationContext, InfoAritstActivity::class.java)
                .putExtra("artist", it))
        }
        val trackAdapter = TrackAdapter(returnTrackData){
            startActivity(Intent(applicationContext, InfoAritstActivity::class.java)
                .putExtra("track", it))
        }

        binding.sortBtn.setOnClickListener {
            when(binding.sortBtn.text.toString()){
                "가수 기준"->{
                    binding.sortBtn.text = "곡 기준"
                    if(binding.editText.text.toString().count() > 0){
                        getSearchArtistData(binding.editText.text.toString())
                    }
                }
                "곡 기준"->{
                    binding.sortBtn.text = "가수 기준"
                    if(binding.editText.text.toString().count() > 0) {
                        getSearchTrackData(binding.editText.text.toString())
                    }
                }
            }
        }



    }


    private fun getSearchTrackData(inputData: String){
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(SearchTrack::class.java)


        GlobalScope.launch(Dispatchers.IO) {
            val result = retrofit.getSearchArtistData(inputData).awaitResponse()

            Log.d(TAG, "getSearchArtistData: result : $result")

            if (result.isSuccessful) {
                val data = result.body()

                returnTrackData.clear()

                returnTrackData = data!!.results.trackmatches.track as ArrayList<Track>

                withContext(Dispatchers.Main){
                    when(binding.sortBtn.text.toString()){
                        "가수 기준"->binding.recycler.adapter = TrackAdapter(returnTrackData){
                            startActivity(Intent(applicationContext, InfoAritstActivity::class.java)
                                .putExtra("track", it))
                        }
                        "곡 기준"->binding.recycler.adapter = ArtistAdapter(returnArtistData){
                            startActivity(Intent(applicationContext, InfoAritstActivity::class.java)
                                .putExtra("artist", it))
                        }
                    }
                }

                Log.d(TAG, "getSearchTrackData: returnTrackData ")
                }
            }
        }

    private fun getSearchArtistData(inputData: String) {

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(SerachArtist::class.java)


        Log.d(TAG, "getSearchArtistData: 함수 실행됨")


        GlobalScope.launch(Dispatchers.IO) {
            val result = retrofit.getSearchArtistData(inputData).awaitResponse()

            Log.d(TAG, "getSearchArtistData: result : $result")

            if (result.isSuccessful) {
                val data = result.body()


                returnArtistData = data!!.results.artistmatches.artist as ArrayList<Artist>

                withContext(Dispatchers.Main){
                    when(binding.sortBtn.text.toString()){
                        "가수 기준"->binding.recycler.adapter = TrackAdapter(returnTrackData){
                            startActivity(Intent(applicationContext, InfoAritstActivity::class.java)
                                .putExtra("track", it))
                        }
                        "곡 기준"->binding.recycler.adapter = ArtistAdapter(returnArtistData){
                            startActivity(Intent(applicationContext, InfoAritstActivity::class.java)
                                .putExtra("artist", it))
                        }
                    }
                }
            }
        }
    }
}