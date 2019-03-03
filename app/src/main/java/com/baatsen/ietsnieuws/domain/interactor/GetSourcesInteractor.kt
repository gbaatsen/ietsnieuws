package com.baatsen.ietsnieuws.domain.interactor

import com.baatsen.ietsnieuws.domain.SourcesRepository
import com.baatsen.ietsnieuws.domain.model.Source
import io.reactivex.Single

class GetSourcesInteractor(private val repository: SourcesRepository) {

    fun execute(): Single<List<Source>> {
        return repository.getSources()
    }
}