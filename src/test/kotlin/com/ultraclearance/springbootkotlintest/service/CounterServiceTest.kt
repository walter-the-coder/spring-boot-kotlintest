package com.ultraclearance.springbootkotlintest.service

import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec

class CounterServiceTest : FunSpec({

    test("the counter should count from the initial value when the initial value is given") {
        val counter = CounterService(1)
        counter.countDown()
        counter.count shouldBe 0
    }

    test("the counter should count from 0 when no initial value is given") {
        val counter = CounterService()
        counter.countUp()
        counter.count shouldBe 1
    }
})
