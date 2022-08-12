package com.example.spaceprobe.domain.usecase.landprobe.exception

class InvalidCommandException(
    val command: String
) : Exception()
