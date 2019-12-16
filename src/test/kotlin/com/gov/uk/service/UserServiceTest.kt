package com.gov.uk.service

import com.gov.uk.model.City.LONDON
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import utils.ModelCreator.aUser

internal class UserServiceTest {

    private val mockHerokuBackendService: HerokuBackendService = mockk()
    private val mockCityService: CityService = mockk()

    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        userService = UserService(mockHerokuBackendService, mockCityService)
    }

    @Nested
    inner class `When getUsersByCity invoked` {

        @Test
        fun `Should request getUsersByCity and pass through the response`() {
            val city = "London"
            val users = listOf(aUser())
            every { mockHerokuBackendService.getUsersByCity(city) } returns users

            val actualUsers = userService.getUsersByCity(city)

            assertEquals(users, actualUsers)
        }

        @Test
        fun `Should return null when and empty response is received from backend service`() {
            val city = "London"
            every { mockHerokuBackendService.getUsersByCity(city) } returns null

            val actualUsers = userService.getUsersByCity(city)

            assertNull(actualUsers)
        }


    }

    @Nested
    inner class `When getUsersByDistance invoked` {

        private val city = "London"
        private val distance = 50.0

        @Test
        fun `Should return null if unsupported city is requested`() {
            val unsupportedCity = "PabloVille"
            every { mockCityService.getCity(unsupportedCity) } returns null

            val actualUsers = userService.getUsersByDistance(unsupportedCity, distance)

            assertNull(actualUsers)
        }

        @Test
        fun `Should return null if and empty response is received from the backend service`() {
            every { mockCityService.getCity(city) } returns LONDON
            every { mockHerokuBackendService.getUsers() } returns null

            val actualUsers = userService.getUsersByDistance(city, distance)

            assertNull(actualUsers)
        }

        @Test
        fun `Should filter users whose current location is further than the distance requested`() {
            val userOutsideLondon = aUser(latitude = 120.0, longitude = 120.0)
            val userInLondon = aUser(latitude = LONDON.latitude, longitude = LONDON.longitude)
            val users = listOf(userOutsideLondon, userInLondon)
            val expectedUsers = listOf(userInLondon)
            every { mockCityService.getCity(city) } returns LONDON
            every { mockHerokuBackendService.getUsers() } returns users

            val actualUsers = userService.getUsersByDistance(city, distance)

            assertEquals(expectedUsers, actualUsers)
        }

    }

}
