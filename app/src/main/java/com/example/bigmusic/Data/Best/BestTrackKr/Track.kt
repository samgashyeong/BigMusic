package com.example.bigmusic.Data.Best.BestTrackKr

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Track(
    @SerializedName(value = "@attr")
    val attr: AttrX,
    val artist: Artist,
    val duration: String,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val streamable: Streamable,
    val url: String
) : Serializable