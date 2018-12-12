package com.baatsen.ietsnieuws.presentation.settings.selectsource

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.baatsen.ietsnieuws.di.AppModule
import com.baatsen.ietsnieuws.domain.interactor.GetSourcesInteractor
import com.baatsen.ietsnieuws.domain.model.Source
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.defaultSharedPreferences
import javax.inject.Inject
import javax.inject.Named

class SelectSourceViewModel @Inject constructor(private val getSourcesInteractor: GetSourcesInteractor,
                                                @Named(AppModule.NAMED_APPLICATION_CONTEXT) private val context: Context
) : ViewModel(),
    SourceAdapter.ClickListener {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isInErrorState: MutableLiveData<Boolean> = MutableLiveData()
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData()
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
        context.defaultSharedPreferences.edit().putString("source", id).commit()
    }

}