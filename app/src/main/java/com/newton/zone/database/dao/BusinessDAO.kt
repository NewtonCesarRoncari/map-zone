package com.newton.zone.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.newton.zone.model.Business

@Dao
interface BusinessDAO {

    @Insert
    fun insert(business: Business)

    @Update
    fun update(business: Business)

    @Delete
    fun remove(business: Business)

    @Query("SELECT * FROM Business ORDER BY business.name")
    fun listAll(): LiveData<MutableList<Business>>

    @Query("SELECT * FROM Business WHERE id = :businessId LIMIT 1")
    fun findById(businessId: String): LiveData<Business>

    @Query("SELECT name FROM Business")
    fun returnAllECNames(): LiveData<List<String>>

    @Query("SELECT address FROM Business")
    fun returnAllAddress(): LiveData<List<String>>

    @RawQuery(observedEntities = [Business::class])
    fun findBusinessFilter(query: SupportSQLiteQuery): LiveData<MutableList<Business>>
}