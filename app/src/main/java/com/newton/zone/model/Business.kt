package com.newton.zone.model

import java.math.BigDecimal

class Business(
    val name: String,
    val address: String,
    val tpv: BigDecimal = BigDecimal.ZERO,
    val segment: String,
    val visit: Visit
)