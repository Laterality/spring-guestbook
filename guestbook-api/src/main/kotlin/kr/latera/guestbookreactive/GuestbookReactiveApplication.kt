package kr.latera.guestbookreactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GuestbookReactiveApplication

fun main(args: Array<String>) {
	runApplication<GuestbookReactiveApplication>(*args)
}
