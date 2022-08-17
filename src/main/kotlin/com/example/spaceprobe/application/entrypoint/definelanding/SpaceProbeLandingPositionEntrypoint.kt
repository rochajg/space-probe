package com.example.spaceprobe.application.entrypoint.definelanding

import com.example.spaceprobe.application.entrypoint.definelanding.entity.RequestSpacialProbe
import com.example.spaceprobe.application.entrypoint.definelanding.entity.toDomain
import com.example.spaceprobe.application.exception.BadRequestException
import com.example.spaceprobe.domain.entity.SpaceProbe
import com.example.spaceprobe.usecase.landprobe.DefineProbeLandingUseCase
import com.example.spaceprobe.usecase.landprobe.exception.InvalidCommandException
import com.example.spaceprobe.usecase.landprobe.exception.ProbeOutOfBoundsException
import com.example.spaceprobe.usecase.landprobe.exception.formattedPlanet
import com.example.spaceprobe.usecase.landprobe.exception.formattedProbe
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/landing-position")
class SpaceProbeLandingPositionEntrypoint(
    private val useCase: DefineProbeLandingUseCase
) {

    @PostMapping
    fun defineLandingLocation(
        @RequestBody body: RequestSpacialProbe
    ): SpaceProbe = try {
        useCase.landingPosition(body.probe.toDomain(), body.commands)
    } catch (ex: InvalidCommandException) {
        throw BadRequestException(
            message = "Command '${ex.command}' is not a valid command",
            cause = ex
        )
    } catch (ex: ProbeOutOfBoundsException) {
        throw BadRequestException(
            message = "Probe can't land outside of planet [${ex.formattedProbe()}, ${ex.formattedPlanet()}]",
            cause = ex
        )
    }
}
