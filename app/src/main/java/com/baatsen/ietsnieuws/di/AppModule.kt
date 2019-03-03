package com.baatsen.ietsnieuws.di

import android.content.SharedPreferences
import com.baatsen.ietsnieuws.data.model.NewsMapper
import com.baatsen.ietsnieuws.data.model.SourceMapper
import com.baatsen.ietsnieuws.data.repository.AppArticleRepository
import com.baatsen.ietsnieuws.data.repository.AppSourcesRepository
import com.baatsen.ietsnieuws.data.service.NewsService
import com.baatsen.ietsnieuws.domain.ArticleRepository
import com.baatsen.ietsnieuws.domain.SourcesRepository
import com.baatsen.ietsnieuws.domain.interactor.GetNewsInteractor
import com.baatsen.ietsnieuws.domain.interactor.GetSourcesInteractor
import com.baatsen.ietsnieuws.presentation.news.ArticleAdapter
import com.baatsen.ietsnieuws.presentation.news.ArticleListViewModel
import com.baatsen.ietsnieuws.presentation.settings.SettingsViewModel
import com.baatsen.ietsnieuws.presentation.settings.selectsource.SelectSourceViewModel
import com.baatsen.ietsnieuws.utils.AndroidSchedulerProvider
import com.baatsen.ietsnieuws.utils.BASE_URL
import com.baatsen.ietsnieuws.utils.SchedulerProvider
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.anko.defaultSharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val retrofit: NewsService = createNewsService()

fun createNewsService(): NewsService {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(client)
        .build()
        .create(NewsService::class.java)
}

val appModule = module {
    single<SharedPreferences> { (androidApplication().defaultSharedPreferences) }

    //ViewModels
    viewModel { ArticleListViewModel(get(), get(), get()) }
    viewModel { SelectSourceViewModel(get(), get()) }
    viewModel { SettingsViewModel() }

    //Repos
    single<ArticleRepository> { AppArticleRepository(retrofit, get(), get()) }
    single<SourcesRepository> { AppSourcesRepository(retrofit, get()) }

    //other stuff
    single<SchedulerProvider> { AndroidSchedulerProvider }
    single<ArticleAdapter> { ArticleAdapter() }
    single<GetNewsInteractor> { GetNewsInteractor(get()) }
    single<GetSourcesInteractor> { GetSourcesInteractor(get()) }
    single<NewsMapper> { NewsMapper() }
    single<SourceMapper> { SourceMapper() }
}