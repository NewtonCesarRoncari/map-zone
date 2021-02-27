package com.newton.zone.view.viewmodel

import androidx.lifecycle.ViewModel
import com.newton.zone.repository.FilterRepository

class FilterViewModel(
    private val repository: FilterRepository
) : ViewModel() {
    fun returnAllECNames() = repository.returnAllECNames()

    fun returnAllAddress() = repository.returnAllAddress()
}