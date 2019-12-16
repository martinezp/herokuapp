package com.gov.uk.library

import java.lang.Math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object HaversineFormula {

    private const val EARTH_RADIUS_IN_MILES = 3950

    fun getDistance(latitude1: Double, longitude1: Double, latitude2: Double, longitude2: Double): Double {
        val latitudeDistance = toRadian(latitude2 - latitude1)
        val longitudeDistance = toRadian(longitude2 - longitude1)
        val a = sin(latitudeDistance / 2) * sin(latitudeDistance / 2) +
                cos(toRadian(latitude1)) * cos(toRadian(latitude2)) *
                sin(longitudeDistance / 2) * sin(longitudeDistance / 2)

        return EARTH_RADIUS_IN_MILES * 2 * atan2(sqrt(a), sqrt(1 - a))
    }

    private fun toRadian(value: Double): Double = value * PI / 180

}
