package com.baatsen.ietsnieuws.data.repository

import com.baatsen.ietsnieuws.data.model.NewsMapper
import com.baatsen.ietsnieuws.data.service.NewsService
import com.baatsen.ietsnieuws.domain.ArticleRepository
import com.baatsen.ietsnieuws.domain.model.Article
import com.baatsen.ietsnieuws.utils.KEY
import io.reactivex.Single
import javax.inject.Inject

class AppArticleRepository @Inject
constructor(private val newsService: NewsService, private val newsMapper: NewsMapper) : ArticleRepository {

    override fun getNews(): Single<List<Article>> {
        return newsService.getNews(KEY)
            .map { newsMapper.transform(it) }
            .singleOrError()
    }
}