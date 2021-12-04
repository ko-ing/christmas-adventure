package solutions

import inputs.day1.ChristmasAdventure1

fun main(args: Array<String>){
    val inputs = ChristmasAdventure1.getInputs()
    val count = inputs.zipWithNext { prev, nxt ->
        prev < nxt
    }.count { it }
    println(count)
}