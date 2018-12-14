package com.baatsen.ietsnieuws.di

import android.app.Application
import com.baatsen.ietsnieuws.IetsNieuwsApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        SettingsActivityModule::class,
        RepositoryModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(ietsNieuwsApp: IetsNieuwsApp)
}
