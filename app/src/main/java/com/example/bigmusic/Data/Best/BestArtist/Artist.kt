package com.example.bigmusic.Data.Best.BestArtist

import java.io.Serializable

data class Artist(
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val playcount: String,
    val streamable: String,
    val url: String
) : Serializable