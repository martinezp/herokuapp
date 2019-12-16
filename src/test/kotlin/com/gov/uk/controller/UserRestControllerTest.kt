package com.gov.uk.controller

import com.gov.uk.service.UserService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.web.bind.annotation.RestController
import utils.ModelCreator.aUser

@ExtendWith(SpringExtension::class)
@WebMvcTest(RestController::class)
internal class UserRestControllerTest {

    @MockkBean
    private lateinit var mockedUserService: UserService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Nested
    inner class `When querying users in a city` {

        @Test
        fun `Should query the UserService and return a json response`() {
            val city = "pabloVille"
            val user = aUser()

            every { mockedUserService.getUsersByCity(city) } returns listOf(user)

            mockMvc.perform(buildGetUsersByCityRequest(city))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", `is`(user.id.toInt())))

            verify { mockedUserService.getUsersByCity(city) }
        }

        private fun buildGetUsersByCityRequest(city: String) = get("/city/${city}/users")
            .contentType(MediaType.APPLICATION_JSON)
    }

    @Nested
    inner class `When searching users by distance` {

        @Test
        fun `Should query the UserService and return a json response`() {
            val city = "pabloVille"
            val distance = 50.0
            val user = aUser()

            every { mockedUserService.getUsersByDistance(city, distance) } returns listOf(user)

            mockMvc.perform(buildGetUsersSearch(city, distance))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", `is`(user.id.toInt())))

            verify { mockedUserService.getUsersByDistance(city, distance) }
        }

        private fun buildGetUsersSearch(city: String, distance: Double) = get("/users/search")
            .contentType(MediaType.APPLICATION_JSON)
            .param("city", city)
            .param("distance", distance.toString())

    }

}
