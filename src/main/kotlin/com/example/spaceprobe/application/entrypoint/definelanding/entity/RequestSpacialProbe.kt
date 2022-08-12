package com.example.spaceprobe.application.entrypoint.definelanding.entity

import com.example.spaceprobe.domain.entity.Direction
import com.example.spaceprobe.domain.entity.Position
import com.example.spaceprobe.domain.entity.SpaceProbe

data class RequestSpacialProbe(
    val commands: String,
    val probe: RequestSpaceProbe
)

data class RequestSpaceProbe(
    val x: Int,
    val y: Int,
    val dir: String
)

fun RequestSpaceProbe.toDomain(): SpaceProbe =
    SpaceProbe(
        position = Position(
            x = this.x,
            y = this.y
        ),
        direction = Direction.valueOf(this.dir)
    )
