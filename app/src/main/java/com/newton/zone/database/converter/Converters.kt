package com.newton.zone.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.*

class Converters {

    @TypeConverter
    fun forBigDecimal(value: BigDecimal?): Double {
        return  value?.toDouble() ?: 0.0
    }

    @TypeConverter
    fun forString(value: Double?): BigDecimal {
        return value?.let { BigDecimal(it) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}