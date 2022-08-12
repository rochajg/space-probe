package com.example.spaceprobe.domain.usecase.landprobe.exception

import com.example.spaceprobe.domain.entity.Position

class ProbeOutOfBoundsException(
    val probePosition: Position,
    val planetSize: Position
) : Exception()

fun ProbeOutOfBoundsException.formattedProbe() =
    "probe(x: ${this.probePosition.x}, y: ${this.probePosition.y})"

fun ProbeOutOfBoundsException.formattedPlanet() =
    "planet(x: ${this.planetSize.x}, y: ${this.planetSize.y})"
