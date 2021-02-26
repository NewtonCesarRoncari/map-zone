package com.newton.zone.model

import org.junit.Assert.*
import org.junit.Test

class QueryCreatorFilterTest {

    private val queryCreatorFilterTest = QueryCreatorFilter()

    @Test
    fun returnSQLByParamsWhenNoHaveParams() {
        val params = hashMapOf<String, String>()
        assertEquals(
            "SELECT * FROM BUSINESS WHERE 1 = 1",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenOnlyHaveName() {
        val params = hashMapOf(NAME to "test")
        assertEquals(
            "SELECT * FROM BUSINESS WHERE 1 = 1 AND name = 'test'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenOnlyHaveAddress() {
        val params = hashMapOf(ADDRESS to "AddressTest")
        assertEquals(
            "SELECT * FROM BUSINESS WHERE 1 = 1 AND address = 'AddressTest'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenOnlyHaveType() {
        val params = hashMapOf(@Params TYPE to "CLIENT")
        assertEquals(
            "SELECT * FROM BUSINESS WHERE 1 = 1 AND type = 'CLIENT'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenTPVIsBetweenCluster() {
        val params = hashMapOf(@Params TPV to ">= '10000' AND tpv <= '20000'")
        assertEquals(
            "SELECT * FROM BUSINESS WHERE 1 = 1 AND tpv >= '10000' AND tpv <= '20000'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenTPVIsDownCluster() {
        val params = hashMapOf(@Params TPV to "< '10000'")
        assertEquals(
            "SELECT * FROM BUSINESS WHERE 1 = 1 AND tpv < '10000'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenTPVIsUpCluster() {
        val params = hashMapOf(@Params TPV to "> '20000'")
        assertEquals(
            "SELECT * FROM BUSINESS WHERE 1 = 1 AND tpv > '20000'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }

    @Test
    fun returnSQLByParamsWhenOnlyHaveSegment() {
        val params = hashMapOf(@Params SEGMENT to "DELIVERY")
        assertEquals(
            "SELECT * FROM BUSINESS WHERE 1 = 1 AND segment = 'DELIVERY'",
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
            "SELECT * FROM BUSINESS WHERE 1 = 1 AND name = 'NameTest' AND" +
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
            "SELECT * FROM BUSINESS WHERE 1 = 1 AND name = 'NameTest' AND" +
                    " address = 'Rua dos Bobos' AND" +
                    " type = 'LEAD'",
            queryCreatorFilterTest.returnByParams(params, "BUSINESS")
        )
    }
}