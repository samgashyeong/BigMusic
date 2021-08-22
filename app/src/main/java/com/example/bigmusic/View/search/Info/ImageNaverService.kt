package com.example.bigmusic.View.search.Info

import com.example.bigmusic.Data.Info.ArtistData.Tag.ArtistTagData
import com.example.bigmusic.Data.Info.Image.ImageSearchData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImageNaverService {
    @GET("v1/search/image")
    fun getImageData(@Query("query") query : String,
                     @Query("display") display : Int,
                     @Header("X-Naver-Client-Id") id : String,
                     @Header("X-Naver-Client-Secret") pw : String,): Call<ImageSearchData>
}