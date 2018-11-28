package com.baatsen.ietsnieuws.presentation.news

import android.arch.lifecycle.MutableLiveData
import com.baatsen.ietsnieuws.base.BaseViewModel
import com.baatsen.ietsnieuws.domain.interactor.GetNewsInteractor
import com.baatsen.ietsnieuws.domain.model.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleListViewModel : BaseViewModel(), ArticleAdapter.ClickListener {

    @Inject
    lateinit var getNewsInteractor: GetNewsInteractor

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val urlToOpen = MutableLiveData<String>()
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
                .doOnSubscribe { onStartGetNews() }
                .subscribe(
                        { result -> onNewsReceived(result) },
                        { onError() }
                )
    }

    private fun onStartGetNews() {
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

    override fun onArticleClick(url: String?) {
        url?.let { urlToOpen.value = it }
    }

}