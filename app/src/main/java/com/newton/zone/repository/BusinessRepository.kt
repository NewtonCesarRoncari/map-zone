package com.newton.zone.repository

import com.newton.zone.database.dao.BusinessDAO
import com.newton.zone.model.Business
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BusinessRepository(private val dao: BusinessDAO) {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    fun insert(business: Business) = scope.launch { dao.insert(business) }

    fun update(business: Business) = scope.launch {
        dao.update(business)
    }

    fun listAll() = dao.listAll()
}