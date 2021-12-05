package solutions.day3

import kotlin.math.pow

fun main() {
    val inputs = getInputs()
    val digits = inputs.first().count()

    var temp = inputs
    var temp2 = inputs

    repeat(digits) { idx ->
        if (temp.size > 1) {
            temp = filterCandidates(temp, idx, true)
        }
        if (temp2.size > 1) {
            temp2 = filterCandidates(temp2, idx, false)
        }
    }

    val oxygenGeneratorRating = temp.first().toDecimal()
    val co2ScrubberRating = temp2.first().toDecimal()

    println(oxygenGeneratorRating * co2ScrubberRating)
}

fun filterCandidates(candidates: List<List<Int>>, idx: Int, checkDominant: Boolean = true): List<List<Int>> {
    val onesCount = candidates.map { it[idx] }.count { it == 1 }
    val zerosCount = candidates.map { it[idx] }.count { it == 0 }

    val matchingNumber = if (checkDominant) {
        if (onesCount >= zerosCount) 1 else 0
    } else {
        if (onesCount >= zerosCount) 0 else 1
    }

    return candidates.filter { it[idx] == matchingNumber }
}