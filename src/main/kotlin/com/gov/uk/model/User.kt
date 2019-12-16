package com.gov.uk.model

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    val id: Long,
    @JsonAlias("first_name")
    val firstName: String,
    @JsonAlias("last_name")
    val lastName: String,
    val email: String,
    @JsonAlias("ip_address")
    val ipAddress: String,
    val latitude: Double,
    val longitude: Double,
    val city: String? = null
)
