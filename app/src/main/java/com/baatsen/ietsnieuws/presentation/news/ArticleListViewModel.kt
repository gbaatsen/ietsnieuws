package com.baatsen.ietsnieuws.presentation.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.baatsen.ietsnieuws.di.AppModule
import com.baatsen.ietsnieuws.domain.interactor.GetNewsInteractor
import com.baatsen.ietsnieuws.domain.model.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.defaultSharedPreferences
import javax.inject.Inject
import javax.inject.Named

class ArticleListViewModel @Inject constructor(
    private val getNewsInteractor: GetNewsInteractor,
    @Named(AppModule.NAMED_APPLICATION_CONTEXT) context: Context) : ViewModel(),
    ArticleAdapter.ClickListener {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val urlToOpen = MutableLiveData<String>()
    val isInErrorState: MutableLiveData<Boolean> = MutableLiveData()
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData()
    val articleAdapter = ArticleAdapter(this)
    var source = ""
    val sharedPreferences = context.defaultSharedPreferences

    private lateinit var subscription: Disposable

    init {
        source = context.defaultSharedPreferences.getString("source", "rtl-nieuws")
        loadArticles()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun refresh() {
        isRefreshing.value = true
        loadArticles()
    }

    fun loadArticles() {
        subscription = getNewsInteractor.execute(source)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStartLoading() }
            .subscribe(
                { result -> onNewsReceived(result) },
                { onError() }
            )
    }

    private fun onStartLoading() {
        isLoading.value = true
    }

    private fun onNewsReceived(articleList: List<Article>) {
        isLoading.value = false
        isInErrorState.value = false
        isRefreshing.value = false
        articleAdapter.updateArticleList(articleList)
        articleAdapter.notifyDataSetChanged()
    }

    private fun onError() {
        isLoading.value = false
        isInErrorState.value = true
        isRefreshing.value = false
    }

    override fun onClick(url: String?) {
        url?.let { urlToOpen.value = it }
    }

}