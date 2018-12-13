package com.baatsen.ietsnieuws.utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AndroidSchedulerProvider : SchedulerProvider {

    override fun computation() = Schedulers.computation()

    override fun io() = Schedulers.io()

    override fun ui() = AndroidSchedulers.mainThread()

}