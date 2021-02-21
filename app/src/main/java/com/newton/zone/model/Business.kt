package com.newton.zone.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.math.BigDecimal

@Entity
class Business(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val address: String,
    val tpv: BigDecimal = BigDecimal.ZERO,
    val segment: Segment,
    val visit: Visit
): Serializable