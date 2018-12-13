package com.baatsen.ietsnieuws.presentation.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.baatsen.ietsnieuws.SingleLiveEvent
import com.baatsen.ietsnieuws.domain.interactor.GetNewsInteractor
import com.baatsen.ietsnieuws.domain.model.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(
    private val getNewsInteractor: GetNewsInteractor) : ViewModel(),
    ArticleAdapter.ClickListener {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val urlToOpen = SingleLiveEvent<String>()
    val isInErrorState: MutableLiveData<Boolean> = MutableLiveData()
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData()
    val articleAdapter = ArticleAdapter(this)


    private lateinit var subscription: Disposable

    init {
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
        subscription = getNewsInteractor.execute()
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