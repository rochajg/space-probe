package com.example.spaceprobe.domain.entity

interface Command

enum class RotateCommand : Command {
    L, R;

    companion object {
        fun exists(s: String): Boolean =
            values().any { it.name == s }
    }
}

enum class MoveCommand(val value: Int) : Command {
    M(1);

    companion object {
        fun exists(s: String): Boolean =
            values().any { it.name == s }
    }
}
