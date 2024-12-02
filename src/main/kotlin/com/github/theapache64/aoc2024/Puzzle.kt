package com.github.theapache64.aoc2024

abstract class Puzzle {
    lateinit var input: String
    fun setInput(input: String) {
        this.input = input
    }

    abstract fun solve(): Pair<Int, Int>
}

fun Puzzle.setupAndStart() {
    setInput(readInput(this::class.simpleName!!))
    val answer = solve()
    println("Part 1 answer is `${answer.first}`")
    println("Part 2 answer is `${answer.second}`")
}
