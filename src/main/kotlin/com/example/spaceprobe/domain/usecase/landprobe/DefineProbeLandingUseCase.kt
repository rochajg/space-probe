package com.example.spaceprobe.domain.usecase.landprobe

import com.example.spaceprobe.domain.entity.Command
import com.example.spaceprobe.domain.entity.Direction
import com.example.spaceprobe.domain.entity.MoveCommand
import com.example.spaceprobe.domain.entity.Position
import com.example.spaceprobe.domain.entity.RotateCommand
import com.example.spaceprobe.domain.entity.SpaceProbe
import com.example.spaceprobe.domain.entity.isOffset
import com.example.spaceprobe.domain.entity.moveX
import com.example.spaceprobe.domain.entity.moveY
import org.springframework.stereotype.Service

@Service
class DefineProbeLandingUseCase {

    companion object {
        private val PLANET_SIZE = Position(x = 5, y = 5)
    }

    fun landingPosition(spaceProbe: SpaceProbe, commands: String): SpaceProbe {
        return parseCommands(commands)
            .fold(spaceProbe) { probe, command -> this.performCommand(spaceProbe = probe, command = command) }
            .also(::isValidPosition)
    }

    private fun parseCommands(commands: String): List<Command?> =
        commands.toList()
            .map(Char::toString)
            .map(String::uppercase)
            .map {
                when {
                    MoveCommand.exists(it) -> MoveCommand.valueOf(it)
                    RotateCommand.exists(it) -> RotateCommand.valueOf(it)
                    else -> null
                }
            }

    private fun performCommand(spaceProbe: SpaceProbe, command: Command?): SpaceProbe =
        when (command) {
            is MoveCommand -> performMovement(spaceProbe, command)
            is RotateCommand -> performRotation(spaceProbe, command)
            else -> throw Exception("Command '$command' is not a valid command")
        }

    private fun isValidPosition(spaceProbe: SpaceProbe) {
        if (PLANET_SIZE.isOffset(spaceProbe.position)) {
            throw Exception("Probe cannot land outside of planet")
        }
    }

    private fun performMovement(spaceProbe: SpaceProbe, command: MoveCommand): SpaceProbe =
        spaceProbe.copy(
            position = when (spaceProbe.direction) {
                Direction.N -> spaceProbe.position.moveY(command.value.unaryPlus())
                Direction.E -> spaceProbe.position.moveX(command.value.unaryPlus())
                Direction.S -> spaceProbe.position.moveY(command.value.unaryMinus())
                Direction.W -> spaceProbe.position.moveX(command.value.unaryMinus())
            }
        )

    private fun performRotation(spaceProbe: SpaceProbe, command: RotateCommand): SpaceProbe =
        spaceProbe.copy(
            direction = when (command) {
                RotateCommand.R -> spaceProbe.direction.rotateClockwise()
                RotateCommand.L -> spaceProbe.direction.rotateCounterClockwise()
            }
        )
}
