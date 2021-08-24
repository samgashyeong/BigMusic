package com.example.bigmusic.View.search.Info.artistAndTrack

import com.example.bigmusic.Data.Info.TrackData.Similare.TrackSimilarData
import com.example.bigmusic.Data.Info.TrackData.Tag.TrackTagData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackDataService {
    @GET("/2.0/?method=track.gettoptags&api_key=d72493a388347ce59a245b8b03ffa740&format=json")
    fun getTrackTagData(@Query("artist") artist : String, @Query("track") track : String) : Call<TrackTagData>

    @GET("/2.0/?method=track.getsimilar&api_key=d72493a388347ce59a245b8b03ffa740&format=json")
    fun getTrackSimilarData(@Query("artist") artist : String, @Query("track") track : String) : Call<TrackSimilarData>
}