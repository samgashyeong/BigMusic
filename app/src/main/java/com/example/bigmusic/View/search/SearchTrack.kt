package com.example.bigmusic.View.search

import com.example.bigmusic.Data.Search.SearchArtist.SearchArtistData
import com.example.bigmusic.Data.Search.SearchTrack.SearchTrackData
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchTrack {
    @GET("/2.0/?method=track.search&api_key=d72493a388347ce59a245b8b03ffa740&format=json")
    fun getSearchArtistData(@Query("track") track : String) : retrofit2.Call<SearchTrackData>
}