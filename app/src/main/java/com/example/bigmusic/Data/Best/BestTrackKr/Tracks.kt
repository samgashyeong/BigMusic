package com.example.bigmusic.Data.Best.BestTrackKr

import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName(value = "@attr")
    val attr: Attr,
    val track: List<Track>
)