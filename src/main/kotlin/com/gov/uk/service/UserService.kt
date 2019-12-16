package com.gov.uk.service

import com.gov.uk.library.HaversineFormula
import com.gov.uk.model.User
import org.springframework.stereotype.Service

@Service
class UserService(
    val herokuBackendService: HerokuBackendService,
    val cityService: CityService
) {

    fun getUsersByCity(city: String): List<User>? = herokuBackendService.getUsersByCity(city)

    fun getUsersByDistance(cityName: String, distance: Double): List<User>? = cityService.getCity(cityName)?.run {
        herokuBackendService.getUsers()?.filter {
            HaversineFormula.getDistance(it.latitude, it.longitude, latitude, longitude) <= distance
        }?.toList()
    }

}
