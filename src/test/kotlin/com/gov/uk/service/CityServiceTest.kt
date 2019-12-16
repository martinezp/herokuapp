package com.gov.uk.service

import com.gov.uk.model.City
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CityServiceTest {

    private lateinit var cityService: CityService

    @BeforeEach
    fun setUp() {
        cityService = CityService()
    }

    @Test
    fun `Should return City if the city name is supported`() {
        val city = cityService.getCity("London")

        assertEquals(City.LONDON, city)
    }

    @Test
    fun `Should return null when city name is not supported`() {
        val city = cityService.getCity("PabloVille")

        assertNull(city)
    }

}
