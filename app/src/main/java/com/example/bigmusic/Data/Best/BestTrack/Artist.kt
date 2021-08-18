package com.example.bigmusic.Data.Best.BestTrack

import java.io.Serializable

data class Artist(
    val mbid: String,
    val name: String,
    val url: String
) : Serializable