package day4

val REQUIRED_PASSPORT_FIELDS = listOf("byr", "iyr","eyr","hgt","hcl","ecl","pid")

class Day4 {
    fun run(){
        val count = getDay4Input()
            .splitInputIntoPassportStrings()
            .filter { it.validatePassportStringForRequiredFields() }
            .filter { it.validatePassportStringForRules() }
            .count()

        println(count)
    }

    private fun String.validatePassportStringForRequiredFields(): Boolean {
        val passString = this

        val fields: List<String> = passString.split(*listOf(" ", "\n").toTypedArray())
        val firstFields = fields.map { it.split(":")[0] }
        return firstFields.containsAll(REQUIRED_PASSPORT_FIELDS)
    }

    private fun String.validatePassportStringForRules(): Boolean {
        val passString = this

        val fields: List<String> = passString.split(*listOf(" ", "\n").toTypedArray())
        val mapOfFields: Map<String, String> = fields.associate { it.split(":")[0] to it.split(":")[1] }

        println("$mapOfFields")

        // four digits; at least 1920 and at most 2002
        val byr = mapOfFields["byr"]!!
        if (byr.length != 4 || byr.toInt() < 1920 || byr.toInt() > 2002) {
            println("byr: $byr")
            return false
        }

        // four digits; at least 2010 and at most 2020.
        val iyr = mapOfFields["iyr"]!!
        if (iyr.length != 4 || iyr.toInt() < 2010 || iyr.toInt() > 2020) {
            println("iyr")
            return false
        }

        // four digits; at least 2020 and at most 2030
        val eyr = mapOfFields["eyr"]!!
        if (eyr.length != 4 || eyr.toInt() < 2020 || eyr.toInt() > 2030) {
            println("eyr")
            return false
        }

        // I don't remember or feel like looking up the pattern repeating format thing
        var regex = """[0-9][0-9][0-9]?(cm|in)""".toRegex()

        val hgt = mapOfFields["hgt"]!!
        if (!regex.matches(hgt)) {
            println("hgt")
            return false
        } else{
            if (hgt.contains("cm")) {
                val cms = hgt.split("cm")[0].toInt()
                if (cms < 150 || cms > 193) {
                    println("hgt")
                    return false
                }
            }
            if (hgt.contains("in")) {
                val ins = hgt.split("in")[0].toInt()
                if (ins < 59 || ins > 76) {
                    println("hgt")
                    return false
                }
            }
        }

        regex = """#[a-f0-9][a-f0-9][a-f0-9][a-f0-9][a-f0-9][a-f0-9]""".toRegex()

        val hcl = mapOfFields["hcl"]!!
        if (!regex.matches(hcl)) {
            println("hcl")
            return false
        }

        val allowedEyeColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        val ecl = mapOfFields["ecl"]!!
        if (!allowedEyeColors.contains(ecl)) {
            println("ecl")
            return false
        }

        regex = """[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]""".toRegex()

        val pid = mapOfFields["pid"]!!
        if (!regex.matches(pid)) {
            println("pid")
            return false
        }

        return true
    }
}