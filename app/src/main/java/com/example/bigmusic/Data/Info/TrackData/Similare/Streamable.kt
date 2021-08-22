package com.example.bigmusic.Data.Info.TrackData.Similare

import com.google.gson.annotations.SerializedName

data class Streamable(
    @SerializedName(value = "#text")
    val text: String,
    val fulltrack: String
)