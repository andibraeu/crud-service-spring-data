package de.andi95.crudservicespringdata

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @GetMapping("/")
    fun `return hello world`(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello World!")
    }
}