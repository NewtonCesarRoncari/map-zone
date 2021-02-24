package com.newton.zone.model

import org.junit.Assert.*
import org.junit.Test

class QueryCreatorFilterTest {

    private val queryCreatorFilterTest = QueryCreatorFilter()

    @Test
    fun returnSQLByParamsWhenNoHaveParams() {
        val params = hashMapOf<String, String>()
        assertEquals(
            "SELECT * FROM BUSINESS",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenOnlyHaveName() {
        val params = hashMapOf(@Params NAME to "test")
        assertEquals(
            "SELECT * FROM BUSINESS WHERE name = 'test'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenOnlyHaveAddress() {
        val params = hashMapOf(@Params ADDRESS to "AddressTest")
        assertEquals(
            "SELECT * FROM BUSINESS WHERE address = 'AddressTest'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenOnlyHaveType() {
        val params = hashMapOf(@Params TYPE to "CLIENT")
        assertEquals(
            "SELECT * FROM BUSINESS WHERE type = 'CLIENT'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenOnlyHaveTPV() {
        val params = hashMapOf(@Params TPV to "10.5")
        assertEquals(
            "SELECT * FROM BUSINESS WHERE tpv = '10.5'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenOnlyHaveSegment() {
        val params = hashMapOf(@Params SEGMENT to "DELIVERY")
        assertEquals(
            "SELECT * FROM BUSINESS WHERE segment = 'DELIVERY'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenOnlyHaveNameAndAddress() {
        val params = hashMapOf(
            @Params NAME to "NameTest",
            @Params ADDRESS to "Rua dos Bobos"
        )
        assertEquals(
            "SELECT * FROM BUSINESS WHERE name = 'NameTest' AND" +
                    " address = 'Rua dos Bobos'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenOnlyHaveNameAndAddressAndType() {
        val params = hashMapOf(
            @Params NAME to "NameTest",
            @Params ADDRESS to "Rua dos Bobos",
            @Params TYPE to "LEAD"
        )
        assertEquals(
            "SELECT * FROM BUSINESS WHERE name = 'NameTest' AND" +
                    " address = 'Rua dos Bobos' AND" +
                    " type = 'LEAD'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }
}