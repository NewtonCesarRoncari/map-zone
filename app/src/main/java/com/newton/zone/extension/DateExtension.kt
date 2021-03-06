package com.newton.zone.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.formatForBrazilianDate(): String {
    val formatHour = SimpleDateFormat("dd/MM")
    return formatHour.format(this)
}

@SuppressLint("SimpleDateFormat")
fun Date.formatForBrazilianDateDay(): String {
    val formatHour = SimpleDateFormat("dd")
    return formatHour.format(this)
}

@SuppressLint("SimpleDateFormat")
fun Date.formatForBrazilianDateMonth(): String {
    val formatHour = SimpleDateFormat("MMM")
    return formatHour.format(this)
}

@SuppressLint("SimpleDateFormat")
fun Date.formatForBrazilianHour(): String {
    val formatHour = SimpleDateFormat("HH:mm")
    return formatHour.format(this)
}