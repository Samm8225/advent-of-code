package day5

class Day5 {

    private val maxRows = 128
    private val maxCols = 8
    private var setOfAllSeats = mutableSetOf<Int>()

    fun run() {

        for (i in 0..1023) {
            setOfAllSeats.add(i)
        }

        getTestSeatCodes()
            .map {
                val row = it.substring(0, 7).getRowOfCode()
                val col = it.substring(7).getColOfCode()
                getSeatId(row, col)
            }
            .forEach {
                if (setOfAllSeats.contains(it)) {
                    setOfAllSeats.remove(it)
                }
            }

        println(setOfAllSeats)
    }

    private fun String.getRowOfCode(): Int {
        var currMaxRow = maxRows
        var currMinRow = 1

        this.forEach { char ->
            val diffOfMinAndMax = currMaxRow - currMinRow
            val adjustmentAmount = if (diffOfMinAndMax % 2 == 0) {
                diffOfMinAndMax / 2
            } else {
                (diffOfMinAndMax + 1) / 2
            }
//            println("char: $char currMinRow: $currMinRow currMaxRow: $currMaxRow")

            // update the max row
            if (char == 'F') {
                currMaxRow -= adjustmentAmount
            } else if (char == 'B') {
                currMinRow += adjustmentAmount
            }
        }
        return currMaxRow - 1
    }

    private fun String.getColOfCode(): Int {
        var currMaxCol = maxCols
        var currMinCol = 1

        this.forEach { char ->
            val diffOfMinAndMax = currMaxCol - currMinCol
            val adjustmentAmount = if (diffOfMinAndMax % 2 == 0) { diffOfMinAndMax / 2 } else {(diffOfMinAndMax + 1) / 2}
//            println("char: $char currMinRow: $currMinCol currMaxRow: $currMaxCol")

            // update the max row
            if (char == 'L') {
                currMaxCol -= adjustmentAmount
            } else if (char == 'R') {
                currMinCol += adjustmentAmount
            }
        }

        return currMaxCol - 1
    }

    private fun getSeatId(row: Int, col: Int): Int {
        return 8 * row + col
    }
}