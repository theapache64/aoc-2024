package com.github.theapache64.aoc2024

fun main(args: Array<String>) {
    Day3().setupAndStart()
}

class Day3 : Puzzle() {
    private val regEx = "(mul\\((\\d+),(\\d+)\\)|don't\\(\\)|do\\(\\))".toRegex()
    override fun solve(): Pair<Int, Int> {
        return part1() to part2()
    }

    private fun part1(): Int {
        var mulSum = 0
        for (mul in regEx.findAll(input)) {
            val (key, x, y) = mul.destructured
            if (key.contains("mul")) {
                mulSum += (x.toInt() * y.toInt())
            }
        }
        return mulSum
    }

    private fun part2(): Int {
        var mulSum = 0
        var isEnabled = true
        for (mul in regEx.findAll(input)) {
            val (key, x, y) = mul.destructured.toList()
            when {
                key == "don't()" -> {
                    isEnabled = false
                }

                key == "do()" -> {
                    isEnabled = true
                }

                key.contains("mul") && isEnabled -> {
                    mulSum += (x.toInt() * y.toInt())
                }
            }
        }
        return mulSum
    }

}