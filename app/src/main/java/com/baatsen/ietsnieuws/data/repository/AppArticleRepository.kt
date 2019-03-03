package com.baatsen.ietsnieuws.data.repository

import android.content.SharedPreferences
import com.baatsen.ietsnieuws.data.model.NewsMapper
import com.baatsen.ietsnieuws.data.service.NewsService
import com.baatsen.ietsnieuws.domain.ArticleRepository
import com.baatsen.ietsnieuws.domain.model.Article
import com.baatsen.ietsnieuws.utils.DEFAULT
import com.baatsen.ietsnieuws.utils.KEY
import com.baatsen.ietsnieuws.utils.SOURCE
import io.reactivex.Single

class AppArticleRepository(
    private val newsService: NewsService,
    private val newsMapper: NewsMapper,
    private val sharedPreferences: SharedPreferences
) : ArticleRepository {

    override fun getNews(): Single<List<Article>> {
        val source = sharedPreferences.getString(SOURCE, DEFAULT)
        return newsService.getNews(KEY, source!!) //can force unwrap because of the default value
            .map { newsMapper.transform(it) }
            .singleOrError()
    }
}