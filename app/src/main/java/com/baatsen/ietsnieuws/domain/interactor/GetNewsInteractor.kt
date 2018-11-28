package com.baatsen.ietsnieuws.domain.interactor

import com.baatsen.ietsnieuws.domain.model.Article
import com.baatsen.ietsnieuws.data.repository.AppArticleRepository
import io.reactivex.Single
import javax.inject.Inject

class GetNewsInteractor @Inject constructor(private val articleRepository: AppArticleRepository) {

    fun execute(): Single<List<Article>> {
        return articleRepository.getNews()
    }
}