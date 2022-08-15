package com.example.spaceprobe.domain.entity

import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import org.junit.jupiter.api.Test

class PositionTest {

    @Test
    fun `move position X, positively`() {
        // given
        val initialPosition = Position(x = 0, y = 0)

        // when
        val newPosition = initialPosition.moveX(1)

        // then
        newPosition shouldNotBeSameInstanceAs initialPosition
        newPosition.y shouldBeExactly initialPosition.y
        newPosition.x shouldBeGreaterThan initialPosition.x
    }

    @Test
    fun `move position X, negatively`() {
        // given
        val initialPosition = Position(x = 0, y = 0)

        // when
        val newPosition = initialPosition.moveX(-1)

        // then
        newPosition shouldNotBeSameInstanceAs initialPosition
        newPosition.y shouldBeExactly initialPosition.y
        newPosition.x shouldBeLessThan initialPosition.x
    }

    @Test
    fun `move position Y, positively`() {
        // given
        val initialPosition = Position(x = 0, y = 0)

        // when
        val newPosition = initialPosition.moveY(1)

        // then
        newPosition shouldNotBeSameInstanceAs initialPosition
        newPosition.x shouldBeExactly initialPosition.x
        newPosition.y shouldBeGreaterThan initialPosition.y
    }

    @Test
    fun `move position Y, negatively`() {
        // given
        val initialPosition = Position(x = 0, y = 0)

        // when
        val newPosition = initialPosition.moveY(-1)

        // then
        newPosition shouldNotBeSameInstanceAs initialPosition
        newPosition.x shouldBeExactly initialPosition.x
        newPosition.y shouldBeLessThan initialPosition.y
    }
}
