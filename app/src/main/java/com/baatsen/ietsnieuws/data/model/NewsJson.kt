package com.baatsen.ietsnieuws.data.model

data class NewsJson(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleJson>
)