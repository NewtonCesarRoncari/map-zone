package com.newton.zone.di

import androidx.room.Room
import com.newton.zone.database.ConnectionDatabase
import com.newton.zone.database.dao.BusinessDAO
import com.newton.zone.database.dao.VisitDAO
import com.newton.zone.repository.BusinessRepository
import com.newton.zone.repository.FilterRepository
import com.newton.zone.repository.VisitRepository
import com.newton.zone.view.viewmodel.BusinessViewModel
import com.newton.zone.view.viewmodel.FilterViewModel
import com.newton.zone.view.viewmodel.StateAppComponentsViewModel
import com.newton.zone.view.viewmodel.VisitViewModel
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

val daoModule = module {
    single<BusinessDAO> { get<ConnectionDatabase>().businessDAO() }
    single<VisitDAO> { get<ConnectionDatabase>().visitDAO() }
}

val repositoryModule = module {
    single<BusinessRepository> { BusinessRepository(dao = get()) }
    single<VisitRepository> { VisitRepository(dao = get()) }
    single<FilterRepository> { FilterRepository(businessDAO = get()) }
}

val viewModelModule = module {
    viewModel<StateAppComponentsViewModel> { StateAppComponentsViewModel() }
    viewModel<BusinessViewModel> { BusinessViewModel(repository = get()) }
    viewModel<VisitViewModel> { VisitViewModel(repository = get()) }
    viewModel<FilterViewModel> { FilterViewModel(repository = get()) }
}