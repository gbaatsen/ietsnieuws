package com.baatsen.ietsnieuws.domain.interactor

import com.baatsen.ietsnieuws.data.repository.AppSourcesRepository
import com.baatsen.ietsnieuws.domain.model.Article
import com.baatsen.ietsnieuws.domain.model.Source
import io.reactivex.Single
import javax.inject.Inject

class GetSourcesInteractor @Inject constructor(private val repository: AppSourcesRepository) {

    fun execute(): Single<List<Source>> {
        return repository.getSources()
    }
}