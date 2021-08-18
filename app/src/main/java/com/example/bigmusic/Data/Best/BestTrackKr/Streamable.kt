package com.example.bigmusic.Data.Best.BestTrackKr

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Streamable(
    @SerializedName(value = "#text")
    val text: String,
    val fulltrack: String
) : Serializable