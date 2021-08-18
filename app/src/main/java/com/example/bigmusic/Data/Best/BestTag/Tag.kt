package com.example.bigmusic.Data.Best.BestTag

import java.io.Serializable

data class Tag(
    val name: String,
    val reach: String,
    val streamable: String,
    val taggings: String,
    val url: String,
    val wiki: Wiki
) : Serializable