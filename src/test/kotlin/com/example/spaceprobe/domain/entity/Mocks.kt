package com.example.spaceprobe.domain.entity

import com.example.spaceprobe.domain.entity.forms.Square

fun getMockedPlanet(
    baseX: Int? = 0,
    baseY: Int? = 0,
    sizeX: Int? = 5,
    sizeY: Int? = 5
) = Planet(
    area = Square(
        base = Position(
            x = baseX ?: 0,
            y = baseY ?: 0
        ),
        size = Position(
            x = sizeX ?: 5,
            y = sizeY ?: 5
        )
    )
)

fun getMockedSpaceProbe() = SpaceProbe(
    position = Position(
        x = 1,
        y = 2
    ),
    direction = Direction.N
)
