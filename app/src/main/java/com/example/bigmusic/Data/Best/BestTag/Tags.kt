package com.example.bigmusic.Data.Best.BestTag

import com.google.gson.annotations.SerializedName

data class Tags(
    @SerializedName(value = "@attr")
    val attr: Attr,
    val tag: List<Tag>
)