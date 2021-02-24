package com.newton.zone

import android.app.Application
import com.newton.zone.di.daoModule
import com.newton.zone.di.databaseModule
import com.newton.zone.di.repositoryModule
import com.newton.zone.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    databaseModule,
                    daoModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}