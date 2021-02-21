package com.newton.zone.model

import androidx.annotation.StringDef

@Target(
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
@StringDef(LEAD, CLIENT)
annotation class Type()

const val LEAD = "LEAD"
const val CLIENT = "CLIENT"
