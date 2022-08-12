package com.example.spaceprobe.usecase.landprobe

import com.example.spaceprobe.domain.entity.Command
import com.example.spaceprobe.domain.entity.Direction
import com.example.spaceprobe.domain.entity.MoveCommand
import com.example.spaceprobe.domain.entity.Planet
import com.example.spaceprobe.domain.entity.Position
import com.example.spaceprobe.domain.entity.RotateCommand
import com.example.spaceprobe.domain.entity.SpaceProbe
import com.example.spaceprobe.domain.entity.isOutOfBounds
import com.example.spaceprobe.domain.entity.moveX
import com.example.spaceprobe.domain.entity.moveY
import com.example.spaceprobe.usecase.landprobe.exception.InvalidCommandException
import com.example.spaceprobe.usecase.landprobe.exception.ProbeOutOfBoundsException
import org.springframework.stereotype.Service

@Service
class DefineProbeLandingUseCase {

    companion object {
        private val PLANET = Planet(Position(x = 5, y = 5))
    }

    fun landingPosition(spaceProbe: SpaceProbe, commands: String): SpaceProbe {
        return parseCommands(commands)
            .fold(spaceProbe) { probe, command -> this.performCommand(spaceProbe = probe, command = command) }
            .also(::isValidPosition)
    }

    private fun parseCommands(commands: String): List<Command> =
        commands.toList()
            .map(Char::toString)
            .map(String::uppercase)
            .map {
                when {
                    MoveCommand.exists(it) -> MoveCommand.valueOf(it)
                    RotateCommand.exists(it) -> RotateCommand.valueOf(it)
                    else -> throw InvalidCommandException(it)
                }
            }

    private fun performCommand(spaceProbe: SpaceProbe, command: Command): SpaceProbe =
        when (command) {
            is MoveCommand -> performMovement(spaceProbe, command)
            else -> performRotation(spaceProbe, command as RotateCommand)
        }

    private fun isValidPosition(spaceProbe: SpaceProbe) {
        if (PLANET.isOutOfBounds(spaceProbe.position)) {
            throw ProbeOutOfBoundsException(
                probe = spaceProbe,
                planet = PLANET
            )
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
