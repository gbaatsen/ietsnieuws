package com.baatsen.ietsnieuws.injection.component

import com.baatsen.ietsnieuws.injection.module.NetworkModule
import com.baatsen.ietsnieuws.presentation.news.ArticleListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(articleListViewModel: ArticleListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}