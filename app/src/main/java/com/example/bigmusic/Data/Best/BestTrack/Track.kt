package com.example.bigmusic.Data.Best.BestTrack

import java.io.Serializable

data class Track(
    val artist: Artist,
    val duration: String,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val playcount: String,
    val streamable: Streamable,
    val url: String
) : Serializable