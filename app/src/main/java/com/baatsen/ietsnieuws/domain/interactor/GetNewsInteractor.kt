package com.baatsen.ietsnieuws.domain.interactor

import com.baatsen.ietsnieuws.domain.ArticleRepository
import com.baatsen.ietsnieuws.domain.model.Article
import io.reactivex.Single
import javax.inject.Inject

class GetNewsInteractor @Inject constructor(private val repository: ArticleRepository) {

    fun execute(): Single<List<Article>> {
        return repository.getNews()
    }
}