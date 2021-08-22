package com.example.bigmusic.Data.Search.SearchTrack

import java.io.Serializable

data class Track(
    val artist: String,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
) : Serializable