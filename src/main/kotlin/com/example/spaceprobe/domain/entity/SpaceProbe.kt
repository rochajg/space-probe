package com.example.spaceprobe.domain.entity

data class SpaceProbe(
    val position: Position,
    val direction: Direction
)

data class Position(
    val x: Int,
    val y: Int
)

enum class Direction {
    N {
        override fun rotateClockwise() = E
        override fun rotateCounterClockwise() = W
    },
    E {
        override fun rotateClockwise() = S
        override fun rotateCounterClockwise() = N
    },
    S {
        override fun rotateClockwise() = W
        override fun rotateCounterClockwise() = E
    },
    W {
        override fun rotateClockwise() = N
        override fun rotateCounterClockwise() = S
    };

    abstract fun rotateClockwise(): Direction
    abstract fun rotateCounterClockwise(): Direction
}

fun Position.moveY(steps: Int) = this.copy(
    y = this.y + steps
)

fun Position.moveX(steps: Int) = this.copy(
    x = this.x + steps
)

fun Position.isOffset(pos: Position): Boolean = pos.x > this.x || pos.y > this.y
