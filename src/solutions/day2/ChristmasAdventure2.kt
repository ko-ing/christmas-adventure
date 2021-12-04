package solutions.day2

import inputs.ChristmasAdventure2

enum class Command {
    FORWARD, DOWN, UP
}

fun getEnum(string: String): Command {
    return Command.values().first { it.name.lowercase() == string }
}

fun main() {
    val inputString = ChristmasAdventure2.getInputString()
    val inputs = inputString.trim().lines()
        .map {
            val list = it.split(" ")
            val command = getEnum(list[0])
            val stepSize = list[1].toInt()
            command to stepSize
        }

    val sumPair = inputs
        .groupBy { it.first }
        .map {
            it.key to it.value.sumOf { pair -> pair.second }
        }
        .toMap()

    val multiple = sumPair[Command.FORWARD]!!.times((sumPair[Command.DOWN]!! - sumPair[Command.UP]!!))

    println(multiple)
}