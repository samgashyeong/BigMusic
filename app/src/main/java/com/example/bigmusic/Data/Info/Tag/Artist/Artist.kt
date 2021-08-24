package com.example.bigmusic.Data.Info.Tag.Artist

data class Artist(
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)