package com.example.bigmusic.Data.Info.ArtistData.Similar

data class Artist(
    val image: List<Image>,
    val match: String,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)