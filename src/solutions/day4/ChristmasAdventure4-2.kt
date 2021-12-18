package solutions.day4

fun main() {
    val (inputNumbers, bingo) = getInputs()
    val bingoWidth = bingo.first().size
    val doneBingo = mutableListOf<Int>()

    val crossedLocations = mutableListOf<Location>()

    val numberToLocationMap = getNumberToLocationMap(bingo, bingoWidth)
    val

    run find@ {
        inputNumbers.forEach { number ->
            val numberLocations = numberToLocationMap.remove(number)
            numberLocations?.let { crossedLocations.addAll(it) }

            val colBingo = crossedLocations.groupBy { NthCol(it.nth, it.col) }.values.find { it.count() >= bingoWidth && it.first().nth !in doneBingo }
            val rowBingo = crossedLocations.groupBy { NthRow(it.nth, it.row) }.values.find { it.count() >= bingoWidth && it.first().nth !in doneBingo }

            if (colBingo != null || rowBingo != null) {
                val nth = colBingo?.first()?.nth ?: rowBingo?.first()?.nth!!
                val nthBingo = bingo[nth]

                val boardSum = nthBingo.flatten().map { it.toInt() }.sum()
                val crossedSum = crossedLocations.filter { l -> l.nth == nth }.map { cL -> nthBingo[cL.row][cL.col].toInt() }.sum()

                doneBingo.add(nth)

                println(number)
                if (doneBingo.size == bingo.size || number == "85") {
                    println(number.toInt() * (boardSum - crossedSum))
                    return@find
                }
            }
        }
    }
}

private fun getNumberToLocationMap(
    bingo: List<List<List<String>>>,
    bingoWidth: Int
): Map<String, List<Location>> {
    val numberToLocationMap = mutableMapOf<String, List<Location>>()

    repeat(bingo.size) { i ->
        repeat(bingoWidth) { row ->
            repeat(bingoWidth) { col ->
                val number = bingo[i][row][col]
                val list = numberToLocationMap[number]
                val location = Location(i, row, col)

                numberToLocationMap[number] = list?.let { it + location } ?: listOf(Location(i, row, col))
            }
        }
    }

    return numberToLocationMap.toMap()
}

fun hasBingoCol() {

}

fun hasBingoRow() {

}