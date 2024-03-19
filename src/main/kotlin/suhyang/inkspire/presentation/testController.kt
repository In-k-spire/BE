package suhyang.inkspire.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/test")
@RestController
class TestController {
    @GetMapping("/getTest")
    fun test(): String = "test"
}