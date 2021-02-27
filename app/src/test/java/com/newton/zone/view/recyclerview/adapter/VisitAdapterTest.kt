package com.newton.zone.view.recyclerview.adapter

import android.content.Context
import com.newton.zone.model.Visit
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VisitAdapterTest {

    private val context = Mockito.mock(Context::class.java)

    private val adapter = VisitAdapter(
        context, mutableListOf(
            Visit(),
            Visit()
        )
    )

    @Test
    fun returnSizeOfAdapterWhenHaveTwoVisits() {
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun returnSizeOfAdapterWhenDontHaveVisits() {
        val adapter = BusinessAdapter(context, mutableListOf())
        assertEquals(0, adapter.itemCount)
    }
}