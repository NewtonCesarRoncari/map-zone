package com.newton.zone.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.newton.zone.model.Business

@Dao
interface BusinessDAO {

    @Insert
    fun insert(business: Business)

    @Insert
    fun update(business: Business)

    @Query("SELECT * FROM Business ORDER BY business.name")
    fun listAll(): LiveData<MutableList<Business>>
}