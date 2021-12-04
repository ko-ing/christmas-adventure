package solutions

import inputs.ChristmasAdventure1

fun main(args: Array<String>) {
    val inputs = ChristmasAdventure1.getInputs()
    val windowedList = inputs.windowed(3) {
        it.sum()
    }
    val count = windowedList.zipWithNext { a, b -> a < b }.count { it }
    println(count)
}