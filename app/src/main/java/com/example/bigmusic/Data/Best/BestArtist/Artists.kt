package com.example.bigmusic.Data.Best.BestArtist

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Artists(
    @SerializedName(value = "@attr")
    val attr: Attr,
    val artist: List<Artist>
) : Serializable