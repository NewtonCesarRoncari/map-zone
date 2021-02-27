package com.newton.zone.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.math.BigDecimal

@Entity
class Business(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val address: String,
    var type: String,
    val tpv: BigDecimal = BigDecimal.ZERO,
    val segment: String,
): Serializable {
    @Ignore
    val visit: Visit = Visit()
}