package com.gov.uk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HerokuApp

fun main(args: Array<String>) {
    runApplication<HerokuApp>(*args)
}
