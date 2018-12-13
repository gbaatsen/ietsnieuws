package com.baatsen.ietsnieuws.utils

import io.reactivex.schedulers.Schedulers

object ImmediateSchedulerProvider : SchedulerProvider {

    override fun computation() = Schedulers.trampoline()

    override fun io() = Schedulers.trampoline()

    override fun ui() = Schedulers.trampoline()

}
