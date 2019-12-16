package com.gov.uk.model

enum class City(val latitude: Double, val longitude: Double) {
    LONDON(51.5074, 0.1275);

    companion object {
        fun getCity(city: String): City? = values().find { it.name == city.toUpperCase() }
    }
}
