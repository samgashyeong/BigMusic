package com.example.bigmusic.View.splash

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.bigmusic.Data.Best.BestArtist.Artist
import com.example.bigmusic.Data.Best.BestTag.Tag
import com.example.bigmusic.Data.Best.BestTrackKr.Track
import com.example.bigmusic.R
import com.example.bigmusic.View.MainActivity
import com.example.bigmusic.View.search.SerachArtist
import com.example.bigmusic.View.splash.network.GetBestArtist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class SplashActivity : AppCompatActivity() {
    private val bestArtist: ArrayList<Artist> = ArrayList()
    private val bestTrack : ArrayList<com.example.bigmusic.Data.Best.BestTrack.Track> = ArrayList()
    private val bestTag : ArrayList<Tag> = ArrayList()
    private val bestArtistKr : ArrayList<com.example.bigmusic.Data.Best.BestArtistKr.Artist> = ArrayList()
    private val bestTrackKr : ArrayList<Track> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        getBestTrackKr()


    }


    private fun getBestTrackKr() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetBestArtist::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val getBestArtist = retrofit.getBestTrackKr().awaitResponse()


            Log.d(ContentValues.TAG, "getBestArtist: result : $getBestArtist")

            if (getBestArtist.isSuccessful) {
                val data = getBestArtist.body()


                bestArtist.clear()


                for (i in data!!.tracks.track) {
                    bestTrackKr.add(i)
                }
                Log.d(TAG, "getBestTag: bestTrackKr : $bestTrackKr")

                getBestArtistKr()
            }
        }
    }

    private fun getBestArtistKr() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetBestArtist::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val getBestArtist = retrofit.getBestArtistKr().awaitResponse()


            Log.d(ContentValues.TAG, "getBestArtist: result : $getBestArtist")

            if (getBestArtist.isSuccessful) {
                val data = getBestArtist.body()


                bestArtist.clear()


                for (i in data!!.topartists.artist) {
                    bestArtistKr.add(i)
                }
                Log.d(TAG, "getBestTag: bestArtistKr : $bestArtistKr")

                getBestTag()
            }
        }
    }

    private fun getBestTag() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetBestArtist::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val getBestArtist = retrofit.getBestTag().awaitResponse()


            Log.d(ContentValues.TAG, "getBestArtist: result : $getBestArtist")

            if (getBestArtist.isSuccessful) {
                val data = getBestArtist.body()


                bestArtist.clear()


                for (i in data!!.tags.tag) {
                    bestTag.add(i)
                }
                Log.d(TAG, "getBestTag: bestTag : $bestTag")

                getBestTrack()
            }
        }
    }

    private fun getBestTrack() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetBestArtist::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val getBestArtist = retrofit.getBestTrack().awaitResponse()


            Log.d(ContentValues.TAG, "getBestArtist: result : $getBestArtist")

            if (getBestArtist.isSuccessful) {
                val data = getBestArtist.body()


                bestArtist.clear()


                for (i in data!!.tracks.track) {
                    bestTrack.add(i)
                }
                Log.d(TAG, "bestTrack : $bestTrack")

                getBestArtist()
            }
        }

    }

    private fun getBestArtist() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetBestArtist::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val getBestArtist = retrofit.getBestArtist().awaitResponse()


            Log.d(ContentValues.TAG, "getBestArtist: result : $getBestArtist")

            if (getBestArtist.isSuccessful) {
                val data = getBestArtist.body()


                bestArtist.clear()


                for (i in data!!.artists.artist) {
                    bestArtist.add(i)
                }
                Log.d(TAG, "getBestTag: bestArtist: $bestArtist")

                changeActivity()
            }
        }
    }

    private fun changeActivity(){
        if(bestArtist.isNotEmpty()
            and bestTrack.isNotEmpty()
            and bestTag.isNotEmpty()
            and bestArtistKr.isNotEmpty()
            and bestTrackKr.isNotEmpty()){
            startActivity(Intent(this, MainActivity::class.java)
                .putExtra("bestArtist", bestArtist)
                .putExtra("bestTrack", bestTrack)
                .putExtra("bestTag", bestTag)
                .putExtra("bestArtistKr", bestArtistKr)
                .putExtra("bestTrackKr", bestTrackKr))
            finish()
        }
        else{
            Log.d(TAG, "changeActivity: 데이터를 불러오는데에 실패하였습니다.")
        }
    }
}