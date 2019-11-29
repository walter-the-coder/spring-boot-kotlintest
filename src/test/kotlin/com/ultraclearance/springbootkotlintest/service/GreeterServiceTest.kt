package com.ultraclearance.springbootkotlintest.service

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class GreeterServiceTest : StringSpec({

    "when we have a name, we should be greeted by that name" {
        val name = "Jack"
        val greeter = GreeterService()
        val greeting = greeter.greet(name)
        greeting shouldBe "Hello Jack!"
    }

    "when are anonymous, we should still be greeted" {
        val greeter = GreeterService()
        val greeting = greeter.greet()
        greeting shouldBe "Hello there!"
    }
})
