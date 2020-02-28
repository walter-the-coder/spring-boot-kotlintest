package com.ultraclearance.springbootkotlintest.service

import com.ultraclearance.springbootkotlintest.SpringBootKotlintestApplication
import com.ultraclearance.springbootkotlintest.service.type.Person
import io.kotlintest.TestCase
import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import io.kotlintest.spring.SpringListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [SpringBootKotlintestApplication::class])
class EntranceServiceWithAutowireTest : BehaviorSpec() {

    override fun listeners() = listOf(SpringListener)

    @Autowired
    private lateinit var counterService: CounterService

    @Autowired
    private lateinit var greeterService: GreeterService

    private lateinit var entranceService: EntranceService

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)

        if (testCase.isTopLevel()) {
            entranceService = EntranceService(counterService, greeterService)
            reset()
        }
    }

    fun reset() {
        counterService.count = 0
        entranceService.people.clear()
    }

    init {
        Given("a known person arrive") {
            val person = Person(name = "Jack", anonymous = false)
            When("the person enters") {
                entranceService.enter(person)
                Then("the service should count the person and greet the person by its name") {
                    counterService.count shouldBe 1
                }
            }
            When("the person exits") {
                entranceService.exit(person)
                Then("the service should remove the person from the list of counted people") {
                    counterService.count shouldBe 0
                }
            }
        }

        Given("a an anonymous person arrive") {
            val person = Person(name = "Mr. X", anonymous = true)
            When("the person enters") {
                entranceService.enter(person)
                Then("the service should count the person and greet the person") {
                    counterService.count shouldBe 1
                }
            }
            When("the person exits") {
                entranceService.exit(person)
                Then("the service should remove the person from the list of counted people") {
                    counterService.count shouldBe 0
                }
            }
        }
    }
}
