package com.baatsen.ietsnieuws.data.repository

import android.content.SharedPreferences
import com.baatsen.ietsnieuws.data.model.NewsMapper
import com.baatsen.ietsnieuws.data.service.NewsService
import com.baatsen.ietsnieuws.di.AppModule.Companion.SHARED_PREFERENCES
import com.baatsen.ietsnieuws.domain.ArticleRepository
import com.baatsen.ietsnieuws.domain.model.Article
import com.baatsen.ietsnieuws.utils.DEFAULT
import com.baatsen.ietsnieuws.utils.KEY
import com.baatsen.ietsnieuws.utils.SOURCE
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class AppArticleRepository @Inject
constructor(
    private val newsService: NewsService,
    private val newsMapper: NewsMapper,
    @Named(SHARED_PREFERENCES) private val sharedPrefs: SharedPreferences
) : ArticleRepository {

    override fun getNews(): Single<List<Article>> {
        var source = sharedPrefs.getString(SOURCE, DEFAULT)
        return newsService.getNews(KEY, source!!) //can force unwrap because of the default value
            .map { newsMapper.transform(it) }
            .singleOrError()
    }
}