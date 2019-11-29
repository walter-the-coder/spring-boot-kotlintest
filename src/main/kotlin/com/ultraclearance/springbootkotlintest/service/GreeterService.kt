package com.ultraclearance.springbootkotlintest.service

import org.springframework.stereotype.Component

@Component
class GreeterService {

    fun greet(name: String = "there"): String {
        return "Hello $name!"
    }
}
