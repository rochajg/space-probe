package com.example.spaceprobe.application.entrypoint

import com.example.spaceprobe.application.entrypoint.client.SpaceProbeLandingPositionClient
import com.example.spaceprobe.application.entrypoint.definelanding.entity.RequestSpaceProbe
import com.example.spaceprobe.application.entrypoint.definelanding.entity.RequestSpacialProbe
import com.example.spaceprobe.domain.entity.Direction
import com.example.spaceprobe.domain.entity.Position
import com.example.spaceprobe.domain.entity.SpaceProbe
import com.example.spaceprobe.domain.entity.getMockedPlanet
import com.example.spaceprobe.domain.entity.getMockedSpaceProbe
import com.example.spaceprobe.infrastructure.exception.handler.ApiError
import com.example.spaceprobe.usecase.landprobe.DefineProbeLandingUseCase
import com.example.spaceprobe.usecase.landprobe.exception.InvalidCommandException
import com.example.spaceprobe.usecase.landprobe.exception.ProbeOutOfBoundsException
import io.kotest.assertions.asClue
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpaceProbeLandingPositionEntrypointTest(
    @Autowired
    val client: SpaceProbeLandingPositionClient
) {

    @get:Autowired
    val useCase = mockk<DefineProbeLandingUseCase>()

    @Test
    fun `successfully obtain landing position for valid commands`() {
        // given
        val body = getMockedRequestSpacialProbe()
        val expectedProbe = SpaceProbe(
            position = Position(
                x = 1,
                y = 3
            ),
            direction = Direction.W
        )

        every { useCase.landingPosition(any(), any()) } returns expectedProbe

        // when
        val response = this.client.defineLandingLocation(body)

        // then
        response.status shouldBe 200
        response.body.asClue { it as SpaceProbe }.position shouldBeEqualToComparingFields expectedProbe.position
        response.body.asClue { it as SpaceProbe }.direction shouldBeEqualToComparingFields expectedProbe.direction
    }

    @Test
    fun `given invalid set of commands`() {
        // given
        val body = getMockedRequestSpacialProbe("MLRA")

        every { useCase.landingPosition(any(), any()) } throws InvalidCommandException("A")

        // when
        val response = this.client.defineLandingLocation(body)

        // then
        response.status shouldBe 400
        response.body.asClue { it as ApiError }.error shouldBe "bad_request"
        response.body.asClue { it as ApiError }.message shouldBe "Command 'A' is not a valid command"
    }

    @Test
    fun `given valid set of commands, probe gone out from planet bounds`() {
        // given
        val body = getMockedRequestSpacialProbe("LMM")

        every { useCase.landingPosition(any(), any()) } throws ProbeOutOfBoundsException(
            probe = getMockedSpaceProbe(),
            planet = getMockedPlanet()
        )

        // when
        val response = this.client.defineLandingLocation(body)

        // then
        response.status shouldBe 400
        response.body.asClue { it as ApiError }.error shouldBe "bad_request"
        response.body.asClue { it as ApiError }.message shouldContain "Probe can't land outside of planet"
    }

    private fun getMockedRequestSpacialProbe(commands: String? = null) =
        RequestSpacialProbe(
            commands = commands ?: "LMLMLMLMML",
            probe = RequestSpaceProbe(
                x = 1,
                y = 2,
                dir = "N"
            )
        )
}
