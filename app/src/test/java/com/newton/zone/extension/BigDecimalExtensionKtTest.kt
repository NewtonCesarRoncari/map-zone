package com.newton.zone.extension

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class BigDecimalExtensionKtTest {

    @Test
    fun returnFormatForBrazilianCoin() {
        val stringRetuned = BigDecimal("10.5").formatCoin()
        assertEquals(stringRetuned, " R$ 10,50")
    }

    @Test
    fun returnFormatForBrazilianCoinWhenHaveThousand() {
        val stringRetuned = BigDecimal("2000300.02").formatCoin()
        assertEquals(stringRetuned, " R$ 2.000.300,02")
    }

    @Test
    fun returnFormatForBrazilianCoinWhenHaveMoreCasesRoundUnder() {
        val stringRetuned = BigDecimal("200.02457").formatCoin()
        assertEquals(stringRetuned, " R$ 200,02")
    }

    @Test
    fun returnFormatForBrazilianCoinWhenHaveMoreCasesRoundUpper() {
        val stringRetuned = BigDecimal("200.02557").formatCoin()
        assertEquals(stringRetuned, " R$ 200,03")
    }
}