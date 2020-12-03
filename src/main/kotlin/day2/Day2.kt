package day2

class Day2 {

    fun run() {
        val answer = getInput2()
            .transformInputIntoPasswords()
            .filter { it.validatePass2() }
            .count()
        println("\n\nanswer: $answer\n")
    }
}