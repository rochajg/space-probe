package com.example.spaceprobe.domain.entity

data class Position(
    val x: Int,
    val y: Int
)

fun Position.moveY(steps: Int) = this.copy(
    y = this.y + steps
)

fun Position.moveX(steps: Int) = this.copy(
    x = this.x + steps
)
