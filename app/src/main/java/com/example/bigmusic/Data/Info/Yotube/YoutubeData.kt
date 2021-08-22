package com.example.bigmusic.Data.Info.Yotube

data class YoutubeData(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo,
    val regionCode: String
)