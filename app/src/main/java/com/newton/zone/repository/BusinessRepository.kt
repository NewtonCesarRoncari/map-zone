package com.newton.zone.repository

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SimpleSQLiteQuery
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

    fun update(business: Business) = scope.launch { dao.update(business) }

    fun remove(business: Business) = scope.launch { dao.remove(business) }

    fun listAll() = dao.listAll()

    fun findById(businessId: String) = dao.findById(businessId)

    fun findBusinessFilter(query: String): LiveData<MutableList<Business>> {
        val simpleSQLiteQuery = SimpleSQLiteQuery(query)
        return dao.findBusinessFilter(simpleSQLiteQuery)
    }
}