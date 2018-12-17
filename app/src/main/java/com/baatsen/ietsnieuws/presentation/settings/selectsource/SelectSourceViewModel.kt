package com.baatsen.ietsnieuws.presentation.settings.selectsource

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.SharedPreferences
import com.baatsen.ietsnieuws.SingleLiveEvent
import com.baatsen.ietsnieuws.di.AppModule
import com.baatsen.ietsnieuws.domain.interactor.GetSourcesInteractor
import com.baatsen.ietsnieuws.domain.model.Source
import com.baatsen.ietsnieuws.utils.SOURCE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class SelectSourceViewModel @Inject constructor(
    private val getSourcesInteractor: GetSourcesInteractor,
    @Named(AppModule.SHARED_PREFERENCES) private val sharedPrefs: SharedPreferences
) : ViewModel(),
    SourceAdapter.ClickListener {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isInErrorState: MutableLiveData<Boolean> = MutableLiveData()
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData()
    val sourceSelected: SingleLiveEvent<Void> = SingleLiveEvent()
    val sourceAdapter = SourceAdapter(this)

    private lateinit var subscription: Disposable

    init {
        loadSources()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun loadSources() {
        subscription = getSourcesInteractor.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStartLoading() }
            .subscribe(
                { result -> onSourcesReceived(result) },
                { onError() }
            )
    }

    fun refresh() {
        isRefreshing.value = true
        loadSources()
    }

    private fun onStartLoading() {
        isLoading.value = true
    }

    private fun onSourcesReceived(sourceList: List<Source>) {
        isLoading.value = false
        isInErrorState.value = false
        isRefreshing.value = false
        sourceAdapter.updateSourceList(sourceList)
        sourceAdapter.notifyDataSetChanged()
    }

    private fun onError() {
        isLoading.value = false
        isInErrorState.value = true
        isRefreshing.value = false
    }

    override fun onClick(id: String?) {
        sharedPrefs.edit().putString(SOURCE, id).apply()
        sourceSelected.call()
    }

    fun filter(orEmpty: String) {
        println("gilles: $orEmpty")
    }

}