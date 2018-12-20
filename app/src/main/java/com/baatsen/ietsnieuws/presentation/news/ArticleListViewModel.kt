package com.baatsen.ietsnieuws.presentation.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.baatsen.ietsnieuws.SingleLiveEvent
import com.baatsen.ietsnieuws.domain.interactor.GetNewsInteractor
import com.baatsen.ietsnieuws.domain.model.Article
import com.baatsen.ietsnieuws.utils.SchedulerProvider
import com.baatsen.ietsnieuws.viewmodel.State
import com.baatsen.ietsnieuws.viewmodel.StatesViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(
    private val scheduler: SchedulerProvider,
    @Suppress("MemberVisibilityCanBePrivate") val articleAdapter: ArticleAdapter, //must be public for databinding
    private val getNewsInteractor: GetNewsInteractor
) : ViewModel(),
    ArticleAdapter.ClickListener,
    StatesViewModel {

    override val state = MutableLiveData<State>()

    val urlToOpen = SingleLiveEvent<String>()

    private lateinit var subscription: Disposable

    init {
        articleAdapter.clickListener = this
        reload()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun refresh() {
        reload()
    }

    override fun reload() {
        println("gilles reload news")
        subscription = getNewsInteractor.execute()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe { state.value = State.LOADING }
            .doOnSuccess { state.value = State.READY }
            .subscribe(
                { result -> onNewsReceived(result) },
                { state.value = State.ERROR }
            )
    }

    private fun onNewsReceived(articleList: List<Article>) {
        articleAdapter.updateArticleList(articleList)
        articleAdapter.notifyDataSetChanged()
    }

    override fun onClick(url: String?) {
        url?.let { urlToOpen.value = it }
    }

}