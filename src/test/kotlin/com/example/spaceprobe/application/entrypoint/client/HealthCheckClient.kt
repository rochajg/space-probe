package com.example.spaceprobe.application.entrypoint.client

import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext

@Component
class HealthCheckClient(
    context: WebApplicationContext
) : BaseClient(
    context = context
) {

    fun ping() =
        this.get<String>("/ping")
}
