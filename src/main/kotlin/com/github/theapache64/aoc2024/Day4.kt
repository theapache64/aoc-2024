package com.github.theapache64.aoc2024

fun main(args: Array<String>) {
    Day4().setupAndStart()
}

enum class Direction(
    val rowVal: Int,
    val colVal: Int
) {
    TOP(-1, 0),
    RIGHT(0,1),
    LEFT(0, -1),
    BOTTOM(1, 0),
    BOTTOM_RIGHT(1,1),
    BOTTOM_LEFT(1, -1),
    TOP_RIGHT(-1,1),
    TOP_LEFT(-1, -1),
}

class Day4 : Puzzle() {
    override fun solve(): Pair<Int, Int> {
        return part1().assertAndReturn(2554) to part2().assertAndReturn(1916)
    }

    private fun part1(): Int {
        val charMatrix = input.lines().map { line ->
            line.toList()
        }

        var count = 0
        for (rowIndex in 0..charMatrix.lastIndex) {
            for (colIndex in 0..charMatrix[rowIndex].lastIndex) {
                val char = charMatrix[rowIndex][colIndex]
                if (char == 'X') {
                    charMatrix.word(rowIndex, colIndex, Direction.TOP).onXMAS { count++ }
                    charMatrix.word(rowIndex, colIndex, Direction.BOTTOM).onXMAS { count++ }
                    charMatrix.word(rowIndex, colIndex, Direction.RIGHT).onXMAS { count++ }
                    charMatrix.word(rowIndex, colIndex, Direction.LEFT).onXMAS { count++ }
                    charMatrix.word(rowIndex, colIndex, Direction.TOP_RIGHT).onXMAS { count++ }
                    charMatrix.word(rowIndex, colIndex, Direction.TOP_LEFT).onXMAS { count++ }
                    charMatrix.word(rowIndex, colIndex, Direction.BOTTOM_RIGHT).onXMAS { count++ }
                    charMatrix.word(rowIndex, colIndex, Direction.BOTTOM_LEFT).onXMAS { count++ }
                }
            }
        }
        return count
    }

    private fun part2(): Int {
        val charMatrix = input.lines().map { line -> line.toList() }
        var count = 0
        for (i in 0..charMatrix.lastIndex) {
            for (j in 0..charMatrix[i].lastIndex) {
                val char = charMatrix[i][j]
                if (char == 'A') {
                    try {
                        val topLeft = charMatrix[i - 1][j - 1]
                        val topRight = charMatrix[i - 1][j + 1]
                        val bottomRight = charMatrix[i + 1][j + 1]
                        val bottomLeft = charMatrix[i + 1][j - 1]

                        val isGoodLeftDiagonal =
                            (topLeft == 'M' && bottomRight == 'S') || (topLeft == 'S' && bottomRight == 'M')
                        val isGoodRightDiagonal =
                            (topRight == 'M' && bottomLeft == 'S') || (topRight == 'S' && bottomLeft == 'M')

                        if (isGoodRightDiagonal && isGoodLeftDiagonal) {
                            count++
                        }
                    }catch (e: Exception){
                    }
                }
            }
        }

        return count
    }

    private fun String.onXMAS(onXMAS: () -> Unit) {
        if (this == "XMAS") {
            onXMAS()
        }
    }

    private fun List<List<Char>>.word(rowIndex: Int, colIndex: Int, direction: Direction): String {
        val sb = StringBuilder()
        var rowX = rowIndex
        var colX = colIndex
        return try {
            repeat(4) {
                sb.append("${this[rowX][colX]}")
                rowX += direction.rowVal
                colX += direction.colVal
            }
            return if (sb.toString() == "XMAS") {
                "XMAS"
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }
}