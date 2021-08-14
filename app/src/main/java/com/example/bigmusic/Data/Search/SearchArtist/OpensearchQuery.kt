package com.example.bigmusic.Data.Search.SearchArtist

data class OpensearchQuery(
    val role: String,
    val searchTerms: String,
    val startPage: String
)