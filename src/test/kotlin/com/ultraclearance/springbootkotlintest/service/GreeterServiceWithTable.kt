package com.ultraclearance.springbootkotlintest.service

import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.specs.BehaviorSpec
import io.kotlintest.tables.forAll
import io.kotlintest.tables.headers
import io.kotlintest.tables.row
import io.kotlintest.tables.table

class GreeterServiceWithTable : BehaviorSpec() {

    private val greeterService = GreeterService()

    init {
        table(
                headers("name", "expectedResult"),
                row("Jack", "Hello Jack!"),
                row("Jane", "Hello Jane!"),
                row(null, "Hello there!")
        ).forAll { name: String?, expectedResult: String ->
            Given("a person with name $name") {

                When("the person is greeted") {
                    val greeting = if (name != null) {
                        greeterService.greet(name)
                    } else {
                        greeterService.greet()
                    }

                    Then("he is expected to be greeted with: $expectedResult") {
                        greeting shouldContain expectedResult
                    }
                }
            }
        }
    }
}
