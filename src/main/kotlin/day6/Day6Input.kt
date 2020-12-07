package day6

fun getDay6TestInput(): List<String> {
    val input= """
        abc

        a
        b
        c

        ab
        ac

        a
        a
        a
        a

        b
""".trimIndent()
    return input.split("\n\n")
}

fun getDay6Input(): List<String> {
    val input= """
        REMOVED ON PURPOSE
""".trimIndent()
    return input.split("\n\n")
}