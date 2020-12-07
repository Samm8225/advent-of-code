package day7

import java.lang.IllegalArgumentException

class Day7 {


    fun run() {

        val bags = getDay7TestInput()
            .map { Bag(it.getColorOfRule(), it.getColoredBagsAndAmountPossibleToBeHeld()) }
            .toMutableSet()

        println(bags.part2("shiny gold"))
    }

    private fun Set<Bag>.part2(startingColor: String): Int {
        val bags = this
        val colorsToBags: Map<String, Bag> = bags.associateBy { it.color }

        return howManyNestedBagsForColor(startingColor, colorsToBags)
    }

    private fun howManyNestedBagsForColor(startingColor: String, colorsToBags: Map<String, Bag>): Int {
        val currBags = colorsToBags[startingColor]?.canHoldColorsToAmount ?: throw IllegalArgumentException("bahh")
        return if (currBags.isEmpty()) {
            0
        } else {
            return currBags.map { it.value + (it.value * howManyNestedBagsForColor(it.key, colorsToBags)) }.sum()
        }
    }


    private fun Set<Bag>.part1(startingColor: String): Int {
        val bags = this
        var currBags = bags.filter { it.canBagHoldColor(startingColor) }.toSet()
        val answerBags = mutableSetOf<Bag>()
        while (currBags.isNotEmpty()) {
            answerBags.addAll(currBags)
            val currBagColors = currBags.map { it.color }
            println(currBagColors)
            currBags = mutableSetOf()
            currBagColors.forEach { color ->
                currBags.addAll(bags.filter { it.canBagHoldColor(color) }.toSet())
            }
        }
        return answerBags.size
    }


    private fun String.getColorOfRule(): String {
        val wordsInRule = this.split(" ")
        return wordsInRule[0] + " " + wordsInRule[1]
    }

    private fun String.getColoredBagsAndAmountPossibleToBeHeld(): MutableMap<String, Int> {
        if (this.contains(" no ")) {
            return mutableMapOf()
        }
        val containedBags: List<String> = this
            .split("contain ")[1]
            .split(" ")
            .filter { it.isNotBlank() }
            .filterNot { it.contains("bag") }

        return containedBags
            .chunked(3)
            .map {
                it
            }
            .associate { it[1] + " " + it[2] to it[0].toInt() }
            .toMutableMap()
    }
}