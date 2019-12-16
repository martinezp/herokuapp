package com.gov.uk.config

import io.ktor.client.HttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HttpClientConfig {

    @Bean
    fun httpClient() = HttpClient()

}
