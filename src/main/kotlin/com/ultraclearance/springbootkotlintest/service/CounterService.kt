package com.ultraclearance.springbootkotlintest.service

import org.springframework.stereotype.Component

@Component
class CounterService(var count: Int = 0) {

    fun countUp() {
        count += 1
    }

    fun countDown() {
        count -= 1
    }
}
