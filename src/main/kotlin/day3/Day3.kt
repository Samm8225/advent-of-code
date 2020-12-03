package day3


class Day3 {
    fun run() {

        val inputAsLines = getInputDay3().inputAsLines()

        val firstTestTreeCount = inputAsLines.countTreesInLines(1)
        val secondTestTreeCount = inputAsLines.countTreesInLines(3)
        val thirdTestTreeCount = inputAsLines.countTreesInLines(5)
        val fourthTestTreeCount = inputAsLines.countTreesInLines(7)
        val fifthTestTreeCount = inputAsLines.countTreesInLines(1, 2)

        println(firstTestTreeCount * secondTestTreeCount * thirdTestTreeCount * fourthTestTreeCount * fifthTestTreeCount)
    }

    private fun List<String>.countTreesInLines(moveOverAmount: Int, moveDownAmount: Int = 1): Int {
        var lineIndex = 0
        var linePos = 0
        var treesCount = 0
        val lines = this
        while (lineIndex < lines.size - 1) {
            lineIndex += moveDownAmount // don't bother checking for tree on first line
            val currLine = lines[lineIndex]
            linePos += moveOverAmount
            if (currLine.isTreeAtPosInLine(linePos)) {
                treesCount++
            }
        }
        return treesCount
    }

    private fun String.isTreeAtPosInLine(index: Int): Boolean {
        val line = this
        var growingLine = line
        while (index > growingLine.length - 1) {
            growingLine += line
        }
        println("Curr line index: $index line: $line found tree: ${growingLine[index] == '#'}")
        return growingLine[index] == '#'
    }
}