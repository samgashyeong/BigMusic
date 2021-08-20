package com.example.bigmusic.Data.Search.SearchArtist

import java.io.Serializable

data class Artist(
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
) : Serializable