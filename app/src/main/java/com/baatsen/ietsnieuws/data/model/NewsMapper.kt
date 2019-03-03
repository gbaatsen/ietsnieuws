package com.baatsen.ietsnieuws.data.model

import com.baatsen.ietsnieuws.domain.model.Article

class NewsMapper {
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