package com.example.bigmusic.Data.Best.BestTrack

import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName(value = "@attr")
    val attr: Attr,
    val track: List<Track>
)