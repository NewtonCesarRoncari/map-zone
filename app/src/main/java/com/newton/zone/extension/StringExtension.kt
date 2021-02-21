package com.newton.zone.extension

import com.newton.zone.exception.NegativeLimitInStringException
import java.math.BigDecimal
import java.util.*

fun String.returnUUID(): String {
    return if (this.isEmpty()) UUID.randomUUID().toString() else this
}

fun String.limit(maxCharacters: Int): String {
    if (maxCharacters < 0) throw NegativeLimitInStringException()
    if (this.length > maxCharacters) {
        val firstCharacter = 0
        return "${this.substring(firstCharacter, maxCharacters)}..."
    }
    return this
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