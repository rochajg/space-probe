package com.example.spaceprobe.usecase.landprobe.exception

import com.example.spaceprobe.domain.entity.Planet
import com.example.spaceprobe.domain.entity.SpaceProbe
import com.example.spaceprobe.domain.entity.forms.Square

class ProbeOutOfBoundsException(
    val probe: SpaceProbe,
    val planet: Planet
) : Exception()

fun ProbeOutOfBoundsException.formattedProbe() =
    "probe(x: ${this.probe.position.x}, y: ${this.probe.position.y})"

fun ProbeOutOfBoundsException.formattedPlanet() =
    "planet(${this.planet.area.formattedArea()})"

private fun Square.formattedArea() =
    "base(x: ${this.base.x}, y: ${this.base.y}), bound(x: ${this.size.x}, y: ${this.size.y})"
