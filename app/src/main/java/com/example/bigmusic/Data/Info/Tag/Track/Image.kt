package com.example.bigmusic.Data.Info.Tag.Track

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName(value = "#text")
    val text: String,
    val size: String
)