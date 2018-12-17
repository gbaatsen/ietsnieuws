package com.baatsen.ietsnieuws.presentation.settings.selectsource

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.SharedPreferences
import com.baatsen.ietsnieuws.SingleLiveEvent
import com.baatsen.ietsnieuws.di.AppModule
import com.baatsen.ietsnieuws.domain.interactor.GetSourcesInteractor
import com.baatsen.ietsnieuws.domain.model.Source
import com.baatsen.ietsnieuws.utils.SOURCE
import com.baatsen.ietsnieuws.viewmodel.State
import com.baatsen.ietsnieuws.viewmodel.StatesViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class SelectSourceViewModel @Inject constructor(
    private val getSourcesInteractor: GetSourcesInteractor,
    @Named(AppModule.SHARED_PREFERENCES) private val sharedPrefs: SharedPreferences
) : ViewModel(),
    SourceAdapter.ClickListener, StatesViewModel {

    override val state = MutableLiveData<State>()
    val sourceSelected: SingleLiveEvent<Void> = SingleLiveEvent()
    val sourceAdapter = SourceAdapter(this)

    private lateinit var subscription: Disposable

    init {
        reload()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    override fun reload() {
        subscription = getSourcesInteractor.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { state.value = State.LOADING }
            .doOnSuccess { state.value = State.READY }
            .subscribe(
                { result -> onSourcesReceived(result) },
                { state.value = State.ERROR }
            )
    }

    private fun onSourcesReceived(sourceList: List<Source>) {
        sourceAdapter.updateSourceList(sourceList)
        sourceAdapter.notifyDataSetChanged()
    }

    override fun onClick(id: String?) {
        sharedPrefs.edit().putString(SOURCE, id).apply()
        sourceSelected.call()
    }

    fun filter(orEmpty: String) {
//todo
    }

}