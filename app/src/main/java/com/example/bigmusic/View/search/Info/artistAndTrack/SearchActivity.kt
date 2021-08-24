package com.example.bigmusic.View.search.Info.artistAndTrack

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
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
import com.example.bigmusic.View.search.Info.ImageNaverService
import com.example.bigmusic.View.search.Info.artistAndTrack.adapter.artist.InfoArtistSimilarAdapter
import com.example.bigmusic.View.search.Info.artistAndTrack.adapter.artist.InfoArtistTagAdapter
import com.example.bigmusic.View.search.Info.artistAndTrack.adapter.track.InfoTrackSimilarAdapter
import com.example.bigmusic.View.search.Info.artistAndTrack.adapter.track.InfoTrackTagAdapter
import com.example.bigmusic.databinding.ActivityInfoAritstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInfoAritstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_aritst)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_info_aritst)

        if(intent.hasExtra("artist")){
            getArtistData()
            binding.errorYoutubeText.visibility = View.GONE
        }
        else{
            getTrackData()
        }

        binding.toolbar2.title = ""

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.pgb.visibility = View.VISIBLE
        binding.scrollView2.visibility = View.INVISIBLE


//        binding.youTubePlayerView.play("XsX3ATc3FbA")

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
                    Glide.with(this@SearchActivity)
                        .load(result!!.items[0].thumbnail)
                        .error(R.drawable.icon)
                        .placeholder(R.drawable.icon)
                        .into(binding.imageView)

                    binding.scrollView2.visibility = View.VISIBLE
                    binding.pgb.visibility = View.INVISIBLE
                }
                Log.d(TAG, "getImageData: resultNaver = $result")
            }
        }
    }

    private fun sum(a : Int, b : Int):Int{
        return a+b
    }


    private fun getTrackData() {
        val data = intent.getSerializableExtra("track") as Track

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

            if(b.isSuccessful and a.isSuccessful){
                val result = b.body()

                getYoutubeVideoCode(data.name, data.artist)
                getImageData("${data.artist}-${data.name}")
                val tag = result!!.toptags.tag as ArrayList<com.example.bigmusic.Data.Info.TrackData.Tag.Tag>

                Log.d(TAG, "getTrackData: tag : $tag")

                val resultB = a.body()

                val track = resultB!!.similartracks.track as ArrayList<com.example.bigmusic.Data.Info.TrackData.Similare.Track>


                withContext(Dispatchers.Main){
                    binding.tagRecycler.adapter = InfoTrackTagAdapter(tag)
                    if(tag.isEmpty()){
                        binding.noRelateTagText.visibility = View.VISIBLE
                    }
                    binding.similarRecycler.adapter = InfoTrackSimilarAdapter(track)
                    if(track.isEmpty()){
                        binding.noRelateTrackAndArtistText.visibility = View.VISIBLE
                        binding.noRelateTrackAndArtistText.text = "관련된 곡이 없습니다."
                    }
                }

            }
        }
    }

    private fun getYoutubeVideoCode(name: String, artist: String) {
        val api = Retrofit.Builder().baseUrl("https://www.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YoutubeSearchService::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val a = api.getYoutubeVideo((1).toString(), "snippet", "$name-$artist lyrics", SecretKey().youtubeId).awaitResponse()

            Log.d(TAG, "getYoutubeVideoCode: aResult = $a")
            if(a.isSuccessful){
                val result = a.body()

                val videoCode = result!!.items[0].id.videoId

                withContext(Dispatchers.Main){
                    binding.youTubePlayerView.play(videoCode)

                    binding.scrollView2.visibility = View.VISIBLE
                    binding.pgb.visibility = View.INVISIBLE
                    binding.errorYoutubeText.setOnClickListener {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$videoCode") ))
                    }
                }
            }
        }
    }

    private fun getArtistData() {
        val data = intent.getSerializableExtra("artist") as Artist

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
        binding.youtubeLayout.visibility = View.GONE
        binding.youTubePlayerView.visibility = View.GONE
        binding.errorYoutubeText.visibility = View.GONE


        GlobalScope.launch(Dispatchers.IO) {
            val a = api.getArtistSimilar(data.name).awaitResponse()
            val b = api.getArtistTagData(data.name).awaitResponse()

            Log.d(TAG, "getArtistData: b : $b")


            if(a.isSuccessful and b.isSuccessful){
                getImageData(data.name)

                val result = a.body()

                val similarAritstList = result!!.similarartists.artist as ArrayList<com.example.bigmusic.Data.Info.ArtistData.Similar.Artist>

                val  resultB = b.body()

                val tagArtistList = resultB!!.toptags.tag as ArrayList<Tag>
                withContext(Dispatchers.Main){
                    binding.similarRecycler.adapter = InfoArtistSimilarAdapter(similarAritstList)
                    if(similarAritstList.isEmpty()){
                        binding.noRelateTrackAndArtistText.visibility = View.VISIBLE
                    }
                    binding.tagRecycler.adapter = InfoArtistTagAdapter(tagArtistList)
                    if(tagArtistList.isEmpty()){
                        binding.noRelateTrackAndArtistText.visibility = View.VISIBLE
                        binding.noRelateTrackAndArtistText.text = "관련된 아티스트 없습니다."
                    }
                }
            }
        }
    }
}