package com.example.spaceprobe.domain.entity

data class SpaceProbe(
    val position: Position,
    val direction: Direction
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
