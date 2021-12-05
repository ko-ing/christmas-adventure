package solutions.day3

import inputs.ChristmasAdventure3
import kotlin.math.pow

fun getInputs(): List<List<Int>> {
    val inputString = ChristmasAdventure3.getInputString()
    return inputString.trim().lines().map {
        it.split("")
            .filter { it.isNotBlank() }
            .map { digit -> digit.toInt() }
    }
}

fun main() {
    val inputs = getInputs()
    val count = inputs.count()
    val digits = inputs.first().count()

    val decimalMaxOfNDigits = generateSequence { 1 }.take(digits).toList().toDecimal()

    val gammaRate = (0 until digits).toList().map { nth ->
        val nthDigits = inputs.map { it[nth] }
        val onesCount = nthDigits.count { it == 1 }
        if (onesCount * 2 >= count) 1 else 0
    }.toDecimal()

    val epsilonRate = decimalMaxOfNDigits - gammaRate

    println(gammaRate * epsilonRate)
}

fun List<Int>.toDecimal(): Int {
    // 앞자리의 인덱스가 커야하므로 reverse
    return this.reversed().withIndex().sumOf { (index, value)->
        if (value == 1) 2.0.pow(index).toInt() else 0
    }
}