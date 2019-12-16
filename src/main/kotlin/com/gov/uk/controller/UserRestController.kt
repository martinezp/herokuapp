package com.gov.uk.controller

import com.gov.uk.model.User
import com.gov.uk.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserRestController(val userService: UserService) {

    @GetMapping("/city/{city}/users")
    fun getUsersByCity(@PathVariable city: String): List<User>? =
        userService.getUsersByCity(city)

    @GetMapping("/users/search")
    fun getUsersByDistance(@RequestParam city: String, @RequestParam distance: Double): List<User>? =
        userService.getUsersByDistance(city, distance)

}
