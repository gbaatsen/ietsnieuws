package com.baatsen.ietsnieuws.domain.interactor

import com.baatsen.ietsnieuws.domain.SourcesRepository
import com.baatsen.ietsnieuws.domain.model.Source
import io.reactivex.Single
import javax.inject.Inject

class GetSourcesInteractor @Inject constructor(private val repository: SourcesRepository) {

    fun execute(): Single<List<Source>> {
        return repository.getSources()
    }
}