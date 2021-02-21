package com.newton.zone.view.viewmodel

import androidx.lifecycle.ViewModel
import com.newton.zone.model.Business
import com.newton.zone.repository.BusinessRepository

class BusinessViewModel(private val repository: BusinessRepository): ViewModel() {

    fun insert(business: Business) = repository.insert(business)

    fun update(business: Business) = repository.update(business)

    fun listAll() = repository.listAll()
}