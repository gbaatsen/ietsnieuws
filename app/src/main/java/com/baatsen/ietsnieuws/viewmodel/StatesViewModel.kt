package com.baatsen.ietsnieuws.viewmodel

import android.arch.lifecycle.MutableLiveData


interface StatesViewModel {

    val state: MutableLiveData<State>

    fun reload() {
    }
}