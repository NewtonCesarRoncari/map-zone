package com.newton.zone.model

import androidx.annotation.StringDef

@Target(
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
@StringDef(TECHNOLOGY, RESTAURANT, DELIVERY, MARKETPLACE, ORDER, LIBRARY, ENTERPRISE, OTHERS)
annotation class Segment()

const val TECHNOLOGY = "TECONOLOGIA"
const val RESTAURANT = "RESTAURANTE"
const val DELIVERY = "DELIVERY"
const val MARKETPLACE = "MARKETPLACE"
const val ORDER = "VENDA"
const val LIBRARY = "BIBLIOTECA"
const val ENTERPRISE = "EMPRESARIAL"
const val OTHERS = "OUTROS"