package com.baatsen.ietsnieuws.injection.module

import com.baatsen.ietsnieuws.data.repository.AppArticleRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    internal abstract fun provideArticleRepository(articleRepository: AppArticleRepository): AppArticleRepository
}
