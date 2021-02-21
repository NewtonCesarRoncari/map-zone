package com.newton.zone.di

import androidx.room.Room
import com.newton.zone.database.ConnectionDatabase
import com.newton.zone.view.viewmodel.StateAppComponentsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NAME_DATABASE = "mapzone.db"

val databaseModule = module {
    single<ConnectionDatabase> {
        Room.databaseBuilder(
            get(),
            ConnectionDatabase::class.java,
            NAME_DATABASE
        ).build()
    }
}

val viewModelModule = module {
    viewModel<StateAppComponentsViewModel> { StateAppComponentsViewModel() }
}