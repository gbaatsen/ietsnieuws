package com.baatsen.ietsnieuws.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.baatsen.ietsnieuws.IetsNieuwsApp
import com.baatsen.ietsnieuws.data.service.NewsService
import com.baatsen.ietsnieuws.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])

class AppModule {

    @Named(NAMED_APPLICATION_CONTEXT)
    @Provides
    internal fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    internal fun provideResources(application: IetsNieuwsApp): Resources {
        return application.getResources()
    }

    @Singleton
    @Provides
    fun provideGithubService(): NewsService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(client)
            .build()
            .create(NewsService::class.java)
    }

    companion object {
        const val NAMED_APPLICATION_CONTEXT = "applicationContext"
    }
}