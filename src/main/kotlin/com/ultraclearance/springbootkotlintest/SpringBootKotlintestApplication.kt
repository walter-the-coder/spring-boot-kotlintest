package com.ultraclearance.springbootkotlintest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKotlintestApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlintestApplication>(*args)
}
