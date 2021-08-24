package com.example.bigmusic.View.search.Info.artistAndTrack

import com.example.bigmusic.Data.Info.ArtistData.Similar.ArtistSimilarData
import com.example.bigmusic.Data.Info.ArtistData.Tag.ArtistTagData
import com.example.bigmusic.Data.Search.SearchArtist.Artist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistDataService {
    @GET("/2.0/?method=artist.gettoptags&api_key=d72493a388347ce59a245b8b03ffa740&format=json")
    fun getArtistTagData(@Query("artist") artist : String): Call<ArtistTagData>

    @GET("/2.0/?method=artist.getsimilar&api_key=d72493a388347ce59a245b8b03ffa740&format=json")
    fun getArtistSimilar(@Query("artist") artist : String) : Call<ArtistSimilarData>
}