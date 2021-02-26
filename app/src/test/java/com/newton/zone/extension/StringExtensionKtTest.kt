package com.newton.zone.extension

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class StringExtensionKtTest {

    private val uuid = UUID.randomUUID().toString()

    @Test
    fun returnUUIDWhenHaveUUID() {
        val uuidReturned = uuid.returnUUID()
        assertEquals(uuid, uuidReturned)
    }
}