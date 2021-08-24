package com.example.bigmusic.Data.Info.Tag.Track

import com.google.gson.annotations.SerializedName

data class Streamable(
    @SerializedName(value = "#text")
    val text: String,
    val fulltrack: String
)