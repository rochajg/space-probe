package com.example.spaceprobe.domain.entity

import com.example.spaceprobe.domain.entity.forms.Square

data class Planet(
    val area: Square
) {
    constructor(size: Position) : this(
        Square(
            base = Position(x = 0, y = 0),
            size = size
        )
    )
}

fun Planet.isOutOfBounds(position: Position) = this.area.isOutOfBounds(position)
