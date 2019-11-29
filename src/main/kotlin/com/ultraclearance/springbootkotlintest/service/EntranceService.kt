package com.ultraclearance.springbootkotlintest.service

import com.ultraclearance.springbootkotlintest.service.type.Person

class EntranceService(
        private val counterService: CounterService,
        private val greeterService: GreeterService
) {
    val people = mutableListOf<Person>()

    fun enter(person: Person) {
        if (!person.anonymous) {
            greeterService.greet(person.name)
        } else {
            greeterService.greet()
        }
        people.add(person)
        counterService.countUp()
    }

    fun exit(person: Person) {
        people.remove(person)
        counterService.countDown()
    }
}
