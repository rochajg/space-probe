package com.example.spaceprobe.application.entrypoint.healthcheck

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckEntrypoint {

    companion object {
        private const val PONG = "pong"
    }

    @GetMapping("/ping")
    fun ping(): String = PONG
}
