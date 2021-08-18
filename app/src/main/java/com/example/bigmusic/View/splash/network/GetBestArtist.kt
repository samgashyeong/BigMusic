package com.example.bigmusic.View.splash.network

import com.example.bigmusic.Data.Best.BestArtist.TopArtistData
import com.example.bigmusic.Data.Best.BestArtistKr.TopArtistKrData
import com.example.bigmusic.Data.Best.BestTag.TopTagData
import com.example.bigmusic.Data.Best.BestTrack.TopTrackData
import com.example.bigmusic.Data.Best.BestTrackKr.TopTrackKrData
import com.example.bigmusic.Data.Search.SearchArtist.SearchArtistData
import retrofit2.Call
import retrofit2.http.GET

interface GetBestArtist {
    @GET("/2.0/?method=chart.gettopartists&api_key=d72493a388347ce59a245b8b03ffa740&format=json")
    fun getBestArtist() : Call<TopArtistData>

    @GET("/2.0/?method=chart.gettoptracks&api_key=d72493a388347ce59a245b8b03ffa740&format=json")
    fun getBestTrack() : Call<TopTrackData>

    @GET("/2.0/?method=chart.gettoptags&api_key=d72493a388347ce59a245b8b03ffa740&format=json")
    fun getBestTag() : Call<TopTagData>

    @GET("/2.0/?method=geo.gettopartists&country=Korea,%20Republic%20of&api_key=d72493a388347ce59a245b8b03ffa740&format=json")
    fun getBestArtistKr() : Call<TopArtistKrData>

    @GET("/2.0/?method=geo.gettoptracks&country=Korea,%20Republic%20of&api_key=d72493a388347ce59a245b8b03ffa740&format=json")
    fun getBestTrackKr() : Call<TopTrackKrData>
}