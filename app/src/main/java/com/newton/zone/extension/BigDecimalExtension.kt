package com.newton.zone.extension

import android.content.Context
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formatCoin(): String {
    val brazilianFormat = DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return brazilianFormat
        .format(this).replace(
            "R$",
            " R$"
        )
}