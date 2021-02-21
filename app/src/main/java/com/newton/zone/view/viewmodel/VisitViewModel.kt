package com.newton.zone.view.viewmodel

import androidx.lifecycle.ViewModel
import com.newton.zone.model.Visit
import com.newton.zone.repository.VisitRepository

class VisitViewModel(private val repository: VisitRepository) : ViewModel() {

    fun insert(visit: Visit) = repository.insert(visit)

    fun update(visit: Visit) = repository.update(visit)

    fun listAll() = repository.listAll()

    fun findVisitByBusinessId(businessId: String) = repository.findVisitByBusinessId(businessId)
}