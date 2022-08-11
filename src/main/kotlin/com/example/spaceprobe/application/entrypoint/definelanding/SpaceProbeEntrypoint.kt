package com.example.spaceprobe.application.entrypoint.definelanding

import com.example.spaceprobe.application.entrypoint.definelanding.entity.RequestSpaceProbe
import com.example.spaceprobe.application.entrypoint.definelanding.entity.RequestSpacialProbe
import com.example.spaceprobe.domain.entity.Direction
import com.example.spaceprobe.domain.entity.Position
import com.example.spaceprobe.domain.entity.SpaceProbe
import com.example.spaceprobe.domain.usecase.landprobe.DefineProbeLandingUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/landing-position")
class SpaceProbeEntrypoint(
    private val useCase: DefineProbeLandingUseCase
) {

    @PostMapping
    fun defineLandingLocation(
        @RequestBody body: RequestSpacialProbe
    ): SpaceProbe =
        useCase.landingPosition(body.probe.toDomain(), body.commands)

    private fun RequestSpaceProbe.toDomain(): SpaceProbe =
        SpaceProbe(
            position = Position(
                x = this.x,
                y = this.y
            ),
            direction = Direction.valueOf(this.dir)
        )
}
