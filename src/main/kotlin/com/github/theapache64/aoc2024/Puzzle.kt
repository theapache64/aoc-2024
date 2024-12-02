package com.github.theapache64.aoc2024

abstract class Puzzle {
    internal lateinit var input: String
    abstract fun solve(): Pair<Int, Int>
}

fun <T : Puzzle> T.setupAndStart(): T {
    input = readInput(this::class.simpleName!!)
    val answer = solve()
    println("Part 1 answer is `${answer.first}`")
    println("Part 2 answer is `${answer.second}`")
    return this
}
