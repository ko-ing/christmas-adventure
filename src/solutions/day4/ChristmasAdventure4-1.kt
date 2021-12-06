package solutions.day4

import inputs.ChristmasAdventure4

data class Location(val nth: Int, val row: Int, val col: Int)
data class NthRow(val nth: Int, val row: Int)
data class NthCol(val nth: Int, val col: Int)

fun getInputs(): Pair<List<String>, List<List<List<String>>>> {
    val split = ChristmasAdventure4.getInputs().split("\n\n")
    val inputNumbers = split.first().split(",")
    val bingo = split.subList(1, split.size).map { string ->
        string.trim().split("\n").map { it.trim().split("\\s+".toRegex()) }
    }
    return Pair(inputNumbers, bingo)
}

fun main() {
    val (inputNumbers, bingo) = getInputs()
    val bingoWidth = bingo.first().size

    val numberToLocationMap = mutableMapOf<String, List<Location>>()
    val crossedLocations = mutableListOf<Location>()

    repeat(bingo.size) { i ->
        repeat(bingoWidth) { row ->
            repeat(bingoWidth)  { col ->
                val number = bingo[i][row][col]
                val list = numberToLocationMap[number]
                val location = Location(i, row, col)

                numberToLocationMap[number] = list?.let { it + location } ?: listOf(Location(i, row, col))
            }
        }
    }

    run find@ {
        inputNumbers.forEach { number ->
            val numberLocations = numberToLocationMap.remove(number)
            numberLocations?.let { crossedLocations.addAll(it) }

            val colBingo = crossedLocations.groupBy { NthCol(it.nth, it.col) }.values.find { it.count() >= bingoWidth }
            val rowBingo = crossedLocations.groupBy { NthRow(it.nth, it.row) }.values.find { it.count() >= bingoWidth }

            if (colBingo != null || rowBingo != null) {
                val nth = colBingo?.first()?.nth ?: rowBingo?.first()?.nth!!
                val nthBingo = bingo[nth]

                val boardSum = nthBingo.flatten().map { it.toInt() }.sum()
                val crossedSum = crossedLocations.filter { l -> l.nth == nth }.map { cL -> nthBingo[cL.row][cL.col].toInt() }.sum()

                println(number.toInt() * (boardSum - crossedSum))
                return@find
            }
        }
    }

}