package com.newton.zone.database.converter

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class ConvertersTest() {

    @Test
    fun returDoubleForBigDecimal() {
        val doubleReturned = Converters().forBigDecimal(BigDecimal("10.5"))
        assertEquals(doubleReturned, 10.5, 0.0)
    }

    @Test
    fun returnBigDecimalForDouble() {
        val bigDecimalReturned = Converters().forDouble(10.toDouble())
        assertEquals(bigDecimalReturned, BigDecimal.TEN)
    }
}