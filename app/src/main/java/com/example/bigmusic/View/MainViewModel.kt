package com.example.bigmusic.View

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bigmusic.Data.Best.BestArtist.Artist
import com.example.bigmusic.Data.Best.BestTag.Tag
import com.example.bigmusic.Data.Best.BestTrackKr.Track

class MainViewModel : ViewModel() {
    val bestArtist: MutableLiveData<ArrayList<Artist>> = MutableLiveData()
    val bestTrack : MutableLiveData<ArrayList<com.example.bigmusic.Data.Best.BestTrack.Track>> = MutableLiveData()
    val bestTag : MutableLiveData<ArrayList<Tag>> = MutableLiveData()
    val bestArtistKr : MutableLiveData<ArrayList<com.example.bigmusic.Data.Best.BestArtistKr.Artist>> = MutableLiveData()
    val bestTrackKr : MutableLiveData<ArrayList<Track>> = MutableLiveData()
}