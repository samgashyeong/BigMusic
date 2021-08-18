package com.example.bigmusic.Data.Best.BestArtistKr

import com.google.gson.annotations.SerializedName

data class Topartists(
    @SerializedName(value = "@attr")
    val attr: Attr,
    val artist: List<Artist>
)