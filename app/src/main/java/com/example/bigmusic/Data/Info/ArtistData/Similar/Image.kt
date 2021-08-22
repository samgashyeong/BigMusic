package com.example.bigmusic.Data.Info.ArtistData.Similar

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName(value = "#text")
    val text: String,
    val size: String
)