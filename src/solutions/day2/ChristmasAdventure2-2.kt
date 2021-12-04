package solutions.day2

fun main() {
    val inputs = getInputs()

    val horizontal = inputs.filter { it.first == Command.FORWARD }.sumOf { it.second }
    var depth = 0
    var aim = 0

    inputs.forEach {
        when (it.first) {
            Command.DOWN -> aim += it.second
            Command.UP -> aim -= it.second
            Command.FORWARD -> depth += aim * it.second
        }
    }

    println(depth * horizontal)
}