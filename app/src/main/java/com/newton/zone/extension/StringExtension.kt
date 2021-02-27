package com.newton.zone.extension

import java.math.BigDecimal
import java.util.*

fun String.returnUUID(): String {
    return if (this.isEmpty()) UUID.randomUUID().toString() else this
}

fun tryParseBigDecimal(serviceStringValue: String?): BigDecimal {
    return try {
        BigDecimal(serviceStringValue)
    } catch (e: NumberFormatException) {
        BigDecimal.ZERO
    } catch (e: NullPointerException) {
        BigDecimal.ZERO
    }
}