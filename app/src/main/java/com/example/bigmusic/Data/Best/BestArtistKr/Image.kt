package com.example.bigmusic.Data.Best.BestArtistKr

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Image(
    @SerializedName(value = "#text")
    val text: String,
    val size: String
) : Serializable