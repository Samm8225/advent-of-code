package day6

class Day6 {
    fun run() {
        val ans = getDay6TestInput()
            .map { it.part2() }
            .sum()

        println(ans)
    }

    private fun String.part1(): Int {

        val letters = setOf('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')
        val set = this
            .filter { letters.contains(it) } // lazy way to remove any strange chars like new lines or spaces or whatever
            .toSet()
        return set.size
    }

    private fun String.part2(): Int {
        val numRows = this.split("\n").size

        return this
            .filter { this.count { c -> c == it } == numRows } // match instances of individual characters to the number of rows
            .toSet().size
    }
}