package com.example.spaceprobe.domain.entity

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class SpaceProbeTest {

    @ParameterizedTest
    @MethodSource("validClockwiseRotations")
    fun `valid clockwise rotations`(data: TestData) {
        data.from.rotateClockwise() shouldBe data.expected
    }

    @ParameterizedTest
    @MethodSource("validCounterClockwiseRotations")
    fun `valid counter clockwise rotations`(data: TestData) {
        data.from.rotateCounterClockwise() shouldBe data.expected
    }

    companion object {
        @JvmStatic
        private fun validClockwiseRotations() = listOf(
            TestData(from = Direction.N, expected = Direction.E),
            TestData(from = Direction.E, expected = Direction.S),
            TestData(from = Direction.S, expected = Direction.W),
            TestData(from = Direction.W, expected = Direction.N),
        )

        @JvmStatic
        private fun validCounterClockwiseRotations() = listOf(
            TestData(from = Direction.N, expected = Direction.W),
            TestData(from = Direction.E, expected = Direction.N),
            TestData(from = Direction.S, expected = Direction.E),
            TestData(from = Direction.W, expected = Direction.S),
        )
    }

    data class TestData(
        val from: Direction,
        val expected: Direction
    )
}
