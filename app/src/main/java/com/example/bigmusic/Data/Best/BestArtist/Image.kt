package com.example.bigmusic.Data.Best.BestArtist

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Image(
    @SerializedName(value = "#text")
    val text: String,
    val size: String
) : Serializable