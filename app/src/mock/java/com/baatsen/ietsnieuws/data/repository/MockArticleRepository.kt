package com.baatsen.ietsnieuws.data.repository

import com.baatsen.ietsnieuws.data.model.NewsMapper
import com.baatsen.ietsnieuws.data.service.MockNewsService
import com.baatsen.ietsnieuws.domain.ArticleRepository
import com.baatsen.ietsnieuws.domain.model.Article
import io.reactivex.Single
import javax.inject.Inject

class MockArticleRepository @Inject
constructor(
    private val newsService: MockNewsService,
    private val newsMapper: NewsMapper
) : ArticleRepository {

    override fun getNews(): Single<List<Article>> {
        return newsService.getNews()
            .map { newsMapper.transform(it) }
    }
}