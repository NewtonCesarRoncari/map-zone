package com.newton.zone.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.newton.zone.database.converter.Converters
import com.newton.zone.database.dao.BusinessDAO
import com.newton.zone.database.dao.VisitDAO
import com.newton.zone.model.Business
import com.newton.zone.model.Visit

@Database(
    version = 1,
    entities = [Business::class, Visit::class],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ConnectionDatabase : RoomDatabase() {
    abstract fun businessDAO(): BusinessDAO
    abstract fun visitDAO(): VisitDAO
}