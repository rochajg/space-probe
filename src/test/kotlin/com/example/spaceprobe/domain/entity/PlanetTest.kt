package com.example.spaceprobe.domain.entity

import com.example.spaceprobe.domain.entity.forms.Square
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PlanetTest {

    @Nested
    inner class PositionOutOfBound {

        @Test
        fun `given positive position`() {
            // given
            val planet = getMockedPlanet()
            val positivePosition = Position(
                x = 6,
                y = 5
            )

            // when
            // then
            planet.isOutOfBounds(positivePosition) shouldBe true
        }

        @Test
        fun `given negative position`() {
            // given
            val planet = getMockedPlanet()
            val negativePosition = Position(
                x = -1,
                y = 2
            )

            // when
            // then
            planet.isOutOfBounds(negativePosition) shouldBe true
        }
    }

    @Nested
    inner class PositionInBound {

        @Test
        fun `given positive position`() {
            // given
            val planet = getMockedPlanet()
            val positivePosition = Position(
                x = 5,
                y = 5
            )

            // when
            // then
            planet.isOutOfBounds(positivePosition) shouldBe false
        }

        @Test
        fun `given negative position`() {
            // given
            val planet = getMockedPlanet()
            val negativePosition = Position(
                x = 0,
                y = 2
            )

            // when
            // then
            planet.isOutOfBounds(negativePosition) shouldBe false
        }
    }

    private fun getMockedPlanet(
        baseX: Int? = 0,
        baseY: Int? = 0,
        sizeX: Int? = 5,
        sizeY: Int? = 5
    ) = Planet(
        area = Square(
            base = Position(
                x = baseX ?: 0,
                y = baseY ?: 0,
            ),
            size = Position(
                x = sizeX ?: 5,
                y = sizeY ?: 5,
            )
        )
    )
}
