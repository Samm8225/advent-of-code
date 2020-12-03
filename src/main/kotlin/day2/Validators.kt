package day2


fun Password.validatePass1(): Boolean {
    val password = this
    val count = password.pass.count { it == password.keyLetter }
    return count in min..max
}

fun Password.validatePass2(): Boolean {
    val password = this
    val chs = password.pass.toCharArray()
    return mutableListOf(chs[password.min - 1] == password.keyLetter, chs[password.max - 1] == password.keyLetter)
        .filter { it }
        .count() % 2 == 1
}
