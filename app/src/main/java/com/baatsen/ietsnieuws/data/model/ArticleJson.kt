package com.baatsen.ietsnieuws.data.model

data class ArticleJson(
    val source: SourceJson?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)