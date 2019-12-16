package com.gov.uk.service

import com.gov.uk.model.City
import org.springframework.stereotype.Component

@Component
class CityService {

    fun getCity(cityName: String): City? = City.getCity(cityName)

}
