package com.newton.zone.repository

import com.newton.zone.database.dao.BusinessDAO

class FilterRepository(
    private val businessDAO: BusinessDAO
) {
    fun returnAllECNames() = businessDAO.returnAllECNames()

    fun returnAllAddress() = businessDAO.returnAllAddress()
}