package com.ultraclearance.springbootkotlintest.service

import com.ultraclearance.springbootkotlintest.service.type.Person
import io.kotlintest.TestCase
import io.kotlintest.specs.BehaviorSpec
import io.mockk.*

class EntranceServiceTest : BehaviorSpec() {

    private val counterService: CounterService = mockk()
    private val greeterService: GreeterService = mockk()
    private var entranceService = EntranceService(counterService, greeterService)

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)

        if (testCase.isTopLevel()) {
            clearAllMocks()
            setupMockks()
        }
    }

    fun setupMockks() {
        every { counterService.countUp() } just Runs
        every { greeterService.greet(any()) } returns "whatever"
    }

    init {
        Given("a known person arrive") {
            val person = Person(name = "Jack", anonymous = false)
            When("the person enters") {
                entranceService.enter(person)
                Then("the service should count the person and greet the person by its name") {
                    verify(exactly = 1) { counterService.countUp() }
                    verify(exactly = 1) { greeterService.greet(person.name) }
                }
            }
        }

        Given("a an anonymous person arrive") {
            val person = Person(name = "Mr. X", anonymous = true)
            When("the person enters") {
                entranceService.enter(person)
                Then("the service should count the person and greet the person") {
                    verify(exactly = 1) { counterService.countUp() }
                    verify(exactly = 1) { greeterService.greet("there") }
                }
            }
        }
    }
}
