package com.baatsen.ietsnieuws.domain.interactor

import com.baatsen.ietsnieuws.data.repository.AppArticleRepository
import com.baatsen.ietsnieuws.domain.model.Article
import io.reactivex.Single
import javax.inject.Inject

class GetNewsInteractor @Inject constructor(private val repository: AppArticleRepository) {

    fun execute(source: String): Single<List<Article>> {
        return repository.getNews(source)
    }
}