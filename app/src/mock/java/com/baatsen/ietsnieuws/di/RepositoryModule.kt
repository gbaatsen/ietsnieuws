package com.baatsen.ietsnieuws.di

import com.baatsen.ietsnieuws.data.repository.MockArticleRepository
import com.baatsen.ietsnieuws.data.repository.MockSourcesRepository
import com.baatsen.ietsnieuws.domain.ArticleRepository
import com.baatsen.ietsnieuws.domain.SourcesRepository
import dagger.Binds
import dagger.Module


@Module
abstract class RepositoryModule {

    @Binds
    internal abstract fun provideArticleRepository(articleRepository: MockArticleRepository): ArticleRepository

    @Binds
    internal abstract fun provideSourcesRepository(sourcesRepository: MockSourcesRepository): SourcesRepository

}
