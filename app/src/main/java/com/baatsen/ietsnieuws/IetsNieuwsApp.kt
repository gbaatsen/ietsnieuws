package com.baatsen.ietsnieuws

import android.app.Application
import com.baatsen.ietsnieuws.di.appModule
import org.koin.android.ext.android.startKoin


class IetsNieuwsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(this, listOf(appModule))
    }
}
