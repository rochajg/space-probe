package com.example.spaceprobe.application.entrypoint.client

import com.example.spaceprobe.application.entrypoint.definelanding.entity.RequestSpacialProbe
import com.example.spaceprobe.domain.entity.SpaceProbe
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext

@Component
class SpaceProbeLandingPositionClient(context: WebApplicationContext) : BaseClient(context) {

    fun defineLandingLocation(body: RequestSpacialProbe) =
        this.post<SpaceProbe>(
            url = "/landing-position",
            body = body
        )
}
