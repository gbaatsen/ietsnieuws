package com.baatsen.ietsnieuws.data.repository

import com.baatsen.ietsnieuws.data.model.SourceMapper
import com.baatsen.ietsnieuws.data.service.NewsService
import com.baatsen.ietsnieuws.domain.SourcesRepository
import com.baatsen.ietsnieuws.domain.model.Source
import com.baatsen.ietsnieuws.utils.KEY
import io.reactivex.Single
import javax.inject.Inject

class AppSourcesRepository @Inject
constructor(private val newsService: NewsService, private val sourceMapper: SourceMapper) : SourcesRepository {

    override fun getSources(): Single<List<Source>> {
        return newsService.getSources(KEY)
            .map { sourceMapper.transform(it) }
            .singleOrError()
    }
}