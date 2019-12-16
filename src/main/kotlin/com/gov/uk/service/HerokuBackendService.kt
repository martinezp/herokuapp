package com.gov.uk.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.gov.uk.model.User
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class HerokuBackendService(
    var httpClient: HttpClient,
    var objectMapper: ObjectMapper,
    @Value("\${herokuapp.backend.url}") var herokuBackendUrl: String
) {

    fun getUsers(): List<User>? = runBlocking {
        objectMapper.readValue<List<User>>(httpClient.get<ByteArray>("$herokuBackendUrl/users"))
    }

    fun getUsersByCity(city: String): List<User>? = runBlocking {
        objectMapper.readValue<List<User>?>(httpClient.get<ByteArray>("$herokuBackendUrl/city/${city}/users"))
    }

}


