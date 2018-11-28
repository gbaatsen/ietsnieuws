package com.baatsen.ietsnieuws.base

import android.arch.lifecycle.ViewModel
import com.baatsen.ietsnieuws.injection.component.DaggerViewModelInjector
import com.baatsen.ietsnieuws.injection.component.ViewModelInjector
import com.baatsen.ietsnieuws.injection.module.NetworkModule
import com.baatsen.ietsnieuws.presentation.news.ArticleListViewModel


abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ArticleListViewModel -> injector.inject(this)
        }
    }
}