package com.example.kiosk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KioskKotlinApplication

fun main(args: Array<String>) {
	runApplication<KioskKotlinApplication>(*args)
}
