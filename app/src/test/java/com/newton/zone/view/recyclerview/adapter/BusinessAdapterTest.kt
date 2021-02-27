package com.newton.zone.view.recyclerview.adapter

import android.content.Context
import com.newton.zone.model.Business
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
class BusinessAdapterTest {

    private val context = Mockito.mock(Context::class.java)

    @Spy
    private val adapter = BusinessAdapter(
        context, mutableListOf(
            Business("", "business1", "", "", BigDecimal.ZERO, ""),
            Business("", "business2", "", "", BigDecimal.ZERO, "")
        )
    )

    @Test
    fun returnSizeOfAdapterWhenHaveTwoBusiness() {
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun returnSizeOfAdapterWhenDontHaveBusiness() {
        val adapter = BusinessAdapter(context, mutableListOf())
        assertEquals(0, adapter.itemCount)
    }

    @Test
    fun returnSizeWhenRemoveItemOfAdapter() {
        Mockito.doNothing().`when`(adapter).notifyItemRemoved(1)

        val size = adapter.itemCount
        adapter.remove(1)
        val sizeAfterRemoved = size - 1
        Mockito.verify(adapter).notifyItemRemoved(1)

        assertEquals(sizeAfterRemoved, adapter.itemCount)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun returnExceptionWhenItemNotHaveInList() {
        adapter.remove(3)
    }
}