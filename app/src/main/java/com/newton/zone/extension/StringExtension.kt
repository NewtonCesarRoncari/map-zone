package com.newton.zone.extension

import java.util.*

fun String.returnUUID(): String {
    return if (this.isEmpty()) UUID.randomUUID().toString() else this
}