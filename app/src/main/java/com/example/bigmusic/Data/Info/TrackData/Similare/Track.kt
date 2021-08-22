package com.example.bigmusic.Data.Info.TrackData.Similare

data class Track(
    val artist: Artist,
    val duration: Int,
    val image: List<Image>,
    val match: Double,
    val mbid: String,
    val name: String,
    val playcount: Int,
    val streamable: Streamable,
    val url: String
)