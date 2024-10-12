package com.colornote.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ColorNoteApplication

fun main(args: Array<String>) {
	runApplication<ColorNoteApplication>(*args)
}
