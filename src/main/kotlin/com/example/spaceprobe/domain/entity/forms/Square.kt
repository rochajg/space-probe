package com.example.spaceprobe.domain.entity.forms

import com.example.spaceprobe.domain.entity.Position

data class Square(
    val base: Position,
    val size: Position
) : Form {
    override fun isOutOfBounds(position: Position): Boolean =
        (position.x > (this.base.x + this.size.x) || position.y > (this.base.y + this.size.y)) ||
            (position.x < this.base.x || position.y < this.base.y)
}
