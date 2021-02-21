package com.newton.zone.repository

import com.newton.zone.database.dao.VisitDAO
import com.newton.zone.model.Visit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class VisitRepository(private val dao: VisitDAO) {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    fun insert(visit: Visit) = scope.launch { dao.insert(visit) }

    fun update(visit: Visit) = scope.launch { dao.update(visit) }

    fun listAll() = dao.listAll()

    fun findVisitByBusinessId(businessId: String) = dao.findVisitByBusinessId(businessId)
}