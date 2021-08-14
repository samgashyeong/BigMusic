package com.example.bigmusic.View.search

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.example.bigmusic.Data.Search.SearchArtist.Artist
import com.example.bigmusic.Data.Search.SearchTrack.Track
import com.example.bigmusic.R
import com.example.bigmusic.databinding.ActivitySearchBinding
import com.google.gson.GsonBuilder
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val returnTrackData : ArrayList<Track> = ArrayList()
    private val returnArtistData : ArrayList<Artist> = ArrayList()
    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.editText.addTextChangedListener {
            getSearchArtistData(binding.editText.text.toString())
            getSearchTrackData(binding.editText.text.toString())

            Log.d(TAG, "onCreate: artist : $returnArtistData\ntrack : $returnTrackData")
        }

        binding.sortBtn.setOnClickListener {

        }
    }

    @DelicateCoroutinesApi
    private fun getSearchTrackData(inputData: String){
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com/2.0/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(SearchTrack::class.java)


        GlobalScope.launch(Dispatchers.IO) {
            val result = retrofit.getSearchArtistData(inputData).awaitResponse()

            if (result.isSuccessful) {
                val data = result.body()


                Log.d(TAG, "getSearchTrackData: data result : $data")


//                for (i in data!!.results.trackmatches.track) {
//                    returnTrackData.add(i)
//                }
            }
        }

    }

    @DelicateCoroutinesApi
    private fun getSearchArtistData(inputData: String) {

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com/2.0/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(SerachArtist::class.java)
        val returnArtistData: ArrayList<Artist> = ArrayList()


        Log.d(TAG, "getSearchArtistData: 함수 실행됨")


        GlobalScope.launch(Dispatchers.IO) {
            val result = retrofit.getSearchArtistData(inputData).awaitResponse()

            Log.d(TAG, "getSearchArtistData: result : $result")

            if (result.isSuccessful) {
                val data = result.body()


//                for (i in data!!.results.artistmatches.artist) {
//                    returnArtistData.add(i)
//                }
            }
        }
    }
}