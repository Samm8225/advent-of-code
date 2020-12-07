package day7

data class Bag(
    val color: String,
    val canHoldColorsToAmount: MutableMap<String, Int> // color to how many of that color can be held
) {
    fun canBagHoldColor(color: String): Boolean {
        return canHoldColorsToAmount.containsKey(color)
    }

    fun howManyOfColorCanBeHeld(color: String): Int {
        return canHoldColorsToAmount[color] ?: 0
    }
}
