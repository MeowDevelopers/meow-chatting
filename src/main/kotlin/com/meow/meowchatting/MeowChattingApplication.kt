package com.meow.meowchatting

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.TimeZone

@SpringBootApplication
class MeowChattingApplication

fun main(args: Array<String>) {
    runApplication<MeowChattingApplication>(*args)
}
