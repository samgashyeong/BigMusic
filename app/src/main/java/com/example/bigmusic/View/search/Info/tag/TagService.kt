package com.example.bigmusic.View.search.Info.tag

import com.example.bigmusic.Data.Info.Tag.Artist.TagArtistData
import com.example.bigmusic.Data.Info.Tag.Track.TagTrackData
import retrofit2.http.GET
import retrofit2.http.Query

interface TagService {
    @GET("/2.0/?method=tag.gettopartists&api_key=d72493a388347ce59a245b8b03ffa740&format=json")
    fun getTopArtistData(@Query("tag") tag : String): retrofit2.Call<TagArtistData>

    @GET("/2.0/?method=tag.gettoptracks&api_key=d72493a388347ce59a245b8b03ffa740&format=json")
    fun getTopTrackData(@Query("tag") tag : String): retrofit2.Call<TagTrackData>


}