package com.github.theapache64.aoc2024

fun main(args: Array<String>) {
    val day = Day1() // Change day here to get answers

    val dayId = day::class.simpleName!!.replace("Day", "").toInt()
    val input = getInput(dayId)
    val answer = day.solve(input)
    println("Part 1 answer is `${answer.first}`")
    println("Part 2 answer is `${answer.second}`")
}

fun getInput(dayId: Int): String {
    return getResource("day${dayId}_input.txt")!!.readText()
}