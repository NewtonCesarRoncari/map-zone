package com.newton.zone.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.newton.zone.model.Visit

@Dao
interface VisitDAO {

    @Insert
    fun insert(visit: Visit)

    @Update
    fun update(visit: Visit)

    @Query("SELECT * FROM `Visit` ORDER BY date, hour")
    fun listAll(): LiveData<MutableList<Visit>>

    @Query("SELECT * FROM `Visit` WHERE business_id = :businessId LIMIT 1")
    fun findVisitByBusinessId(businessId: String): LiveData<Visit>
}