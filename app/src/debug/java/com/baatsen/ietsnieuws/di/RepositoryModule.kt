package com.baatsen.ietsnieuws.di

import com.baatsen.ietsnieuws.data.repository.AppArticleRepository
import com.baatsen.ietsnieuws.data.repository.AppSourcesRepository
import com.baatsen.ietsnieuws.domain.ArticleRepository
import com.baatsen.ietsnieuws.domain.SourcesRepository
import dagger.Binds
import dagger.Module


@Module
abstract class RepositoryModule {

    @Binds
    internal abstract fun provideArticleRepository(articleRepository: AppArticleRepository): ArticleRepository

    @Binds
    internal abstract fun provideASourcesRepository(sourcesRepository: AppSourcesRepository): SourcesRepository

}
