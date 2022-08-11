package com.example.spaceprobe.application.entrypoint.definelanding.entity

data class RequestSpacialProbe(
    val commands: String,
    val probe: RequestSpaceProbe
)

data class RequestSpaceProbe(
    val x: Int,
    val y: Int,
    val dir: String
)
