package com.example.bigmusic.View.search

import android.telecom.Call
import com.example.bigmusic.Data.Search.SearchArtist.SearchArtistData
import retrofit2.http.GET
import retrofit2.http.Query

interface SerachArtist {
    @GET("/2.0/?method=artist.search&api_key=d72493a388347ce59a245b8b03ffa740&format=json")
    fun getSearchArtistData(@Query("artist") artist : String) : retrofit2.Call<SearchArtistData>
}