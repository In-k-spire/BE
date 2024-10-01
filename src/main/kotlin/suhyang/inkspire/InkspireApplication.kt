package suhyang.inkspire

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class InkspireApplication

fun main(args: Array<String>) {
	runApplication<InkspireApplication>(*args)
}