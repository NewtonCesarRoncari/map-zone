package com.newton.zone.model

import androidx.annotation.StringDef

@Target(
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
@StringDef(NAME, ADDRESS, TYPE, TPV, SEGMENT)
annotation class Params()

const val NAME = "name"
const val ADDRESS = "address"
const val TYPE = "type"
const val TPV = "tpv"
const val SEGMENT = "segment"