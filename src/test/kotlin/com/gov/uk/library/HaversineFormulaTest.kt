package com.gov.uk.library

import com.gov.uk.library.HaversineFormula.getDistance
import org.junit.jupiter.api.Assertions.assertEquals

import org.junit.jupiter.api.Test


internal class HaversineFormulaTest {

    @Test
    fun `Should return `() {
        val expectedDistance = 957.7448384974037

        val actualDistance = getDistance(10.0,10.0, 20.0, 20.0)

        assertEquals(expectedDistance, actualDistance)
    }
}
