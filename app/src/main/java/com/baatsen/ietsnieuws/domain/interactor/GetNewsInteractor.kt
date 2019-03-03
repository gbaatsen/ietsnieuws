package com.baatsen.ietsnieuws.domain.interactor

import com.baatsen.ietsnieuws.domain.ArticleRepository
import com.baatsen.ietsnieuws.domain.model.Article
import io.reactivex.Single

class GetNewsInteractor(private val repository: ArticleRepository) {

    fun execute(): Single<List<Article>> {
        return repository.getNews()
    }
}