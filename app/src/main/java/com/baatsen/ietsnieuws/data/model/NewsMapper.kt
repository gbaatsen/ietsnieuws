package com.baatsen.ietsnieuws.data.model

import com.baatsen.ietsnieuws.domain.model.Article
import javax.inject.Inject

class NewsMapper @Inject constructor() {
    fun transform(newsJson: NewsJson): List<Article> {

        return newsJson.articles.map {
            Article(
                title = it.title,
                description = it.description,
                urlToArticle = it.url,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content
            )
        }
    }

}