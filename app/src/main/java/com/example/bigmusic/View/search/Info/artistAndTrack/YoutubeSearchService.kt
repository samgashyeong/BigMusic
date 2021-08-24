package com.example.bigmusic.View.search.Info.artistAndTrack

import com.example.bigmusic.Data.Info.Yotube.YoutubeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeSearchService {
    @GET("/youtube/v3/search")
    fun getYoutubeVideo(@Query("maxResults") maxResults : String
    , @Query("part") part : String
    , @Query("q") q : String
    , @Query("key") key : String) : Call<YoutubeData>
}