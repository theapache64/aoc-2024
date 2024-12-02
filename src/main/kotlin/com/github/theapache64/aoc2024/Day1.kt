package com.github.theapache64.aoc2024

import kotlin.math.abs
import kotlin.math.absoluteValue

fun main(args: Array<String>) {
    Day1().setupAndStart()
}

class Day1 : Puzzle() {

    override fun solve(): Pair<Int, Int> {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()
        input.split("\n")
            .forEach { line ->
                line.split("   ").let {
                    leftList.add(it[0].toInt())
                    rightList.add(it[1].toInt())
                }
            }

        leftList.sort()
        rightList.sort()
        require(leftList.size == rightList.size)
        var diff = 0
        var similarityScore = 0
        repeat(leftList.size) { index ->
            val leftNum = leftList[index]
            diff += (leftNum - rightList[index]).absoluteValue
            similarityScore += (leftNum * rightList.count { it == leftNum })
        }

        return diff to similarityScore
    }

    fun solveUsingZip() : Pair<Int, Int> {
        val foo = input.lines().map { string ->
            val (a, b) = string.split("   ")
            a to b
        }
        val theAs = foo.map { pair -> pair.first.toInt() }.sorted()
        val theBs = foo.map { pair -> pair.second.toInt() }.sorted()
        val paired = theAs.zip(theBs)
        val distances = paired.map { pair -> abs(pair.first - pair.second) }
        val sum= paired.sumOf { pair ->
            pair.first * theBs.count { i -> i == pair.first }
        }
       return distances.sum() to sum
    }

    fun solveUsingZip2(): Pair<Int, Int> {
        val sbsList = input.lines().map { line ->
            val (a, b) = line.split("   ")
            a.toInt() to b.toInt()
        }
        val (firstColumn, secondColumn) = sbsList.unzip()
        return part1(firstColumn, secondColumn) to part2(firstColumn, secondColumn)
    }


    private fun part1(
        firstColumn: List<Int>,
        secondColumn: List<Int>,
    ): Int {
        val sortedColumnPairs = firstColumn.sorted().zip(secondColumn.sorted())
        val distances = sortedColumnPairs.sumOf { (firstColumnItem, secondColumnItem) ->
            abs(firstColumnItem - secondColumnItem)
        }
        return distances
    }

    private fun part2(
        firstColumn: List<Int>,
        secondColumn: List<Int>,
    ): Int {
        val sum = firstColumn.sumOf { firstColumnItem: Int ->
            firstColumnItem * secondColumn.count { secondColumnItem -> secondColumnItem == firstColumnItem }
        }
        return sum
    }

    fun solveUsingSubstring(): Pair<Int, Int> {
        val lines = input.lines()
        val regex = Regex("\\s+")
        val (firstPart1, secondPart1) = lines.map { line ->
            line.split(regex).let { it[0].toInt() to it[1].toInt() }
        }.unzip()

        val frequencies: Map<Int, Int> = secondPart1.groupingBy { it }.eachCount()
        val distances = firstPart1.fold(0) { acc, current ->
            acc + current * frequencies.getOrDefault(current, 0)
        }

        val (firstPart2, secondPart2) = lines.map { line ->
            val left = line.substringBefore(" ").toInt()
            val right = line.substringAfterLast(" ").toInt()
            left to right
        }.unzip()

        val sum = firstPart2.sorted().zip(secondPart2.sorted()).sumOf { (first, second) ->
            abs(first - second)
        }

        return distances to sum
    }
}