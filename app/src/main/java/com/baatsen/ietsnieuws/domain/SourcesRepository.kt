package com.baatsen.ietsnieuws.domain

import com.baatsen.ietsnieuws.domain.model.Source
import io.reactivex.Single

interface SourcesRepository {

    fun getSources(): Single<List<Source>>
}