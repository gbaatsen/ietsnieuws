package com.baatsen.ietsnieuws.domain

import com.baatsen.ietsnieuws.domain.model.Article
import io.reactivex.Single

interface ArticleRepository {

    fun getNews(): Single<List<Article>>
}