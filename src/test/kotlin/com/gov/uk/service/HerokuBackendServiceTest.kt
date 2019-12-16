package com.gov.uk.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.fullPath
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import io.ktor.http.headersOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.ModelCreator.aUser

internal class HerokuBackendServiceTest {

    private val objectMapper = jacksonObjectMapper()
    private val mockedHerokuBackendUrl = "https://backend.com"

    @Test
    fun getUsers() {
        val mockedUrl = URLBuilder("$mockedHerokuBackendUrl/users").build()
        val expectedUsers = listOf(aUser(), aUser())
        val serializedUsers = objectMapper.writeValueAsString(expectedUsers)
        val mockedClient = buildMockClient(mockedUrl, serializedUsers)
        val herokuBackendService = HerokuBackendService(mockedClient, objectMapper, mockedHerokuBackendUrl)

        val actualUsers = herokuBackendService.getUsers()

        assertEquals(expectedUsers, actualUsers)
    }

    @Test
    fun getUsersByCity() {
        val city = "PabloVille"
        val mockedUrl = URLBuilder("$mockedHerokuBackendUrl/city/$city/users").build()
        val expectedUsers = listOf(aUser(), aUser())
        val serializedUsers = objectMapper.writeValueAsString(expectedUsers)
        val mockedClient = buildMockClient(mockedUrl, serializedUsers)
        val herokuBackendService = HerokuBackendService(mockedClient, objectMapper, mockedHerokuBackendUrl)

        val actualUsers = herokuBackendService.getUsersByCity(city)

        assertEquals(expectedUsers, actualUsers)
    }

    fun buildMockClient(mockedUrl: Url, mockedUsers: String) = HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                when (request.url) {
                    mockedUrl -> {
                        val responseHeaders = headersOf("Content-Type" to listOf(ContentType.Text.Plain.toString()))
                        respond(mockedUsers, headers = responseHeaders)
                    }
                    else -> error("Unhandled ${request.url.fullPath}")
                }
            }
        }
    }

}


