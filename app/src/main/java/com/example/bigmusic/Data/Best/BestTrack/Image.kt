package com.example.bigmusic.Data.Best.BestTrack

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Image(
    @SerializedName(value = "#text")
    val text: String,
    val size: String
) : Serializable