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
                    charMatrix.isXmas(rowIndex, colIndex, Direction.TOP).ifTrue { count++ }
                    charMatrix.isXmas(rowIndex, colIndex, Direction.BOTTOM).ifTrue { count++ }
                    charMatrix.isXmas(rowIndex, colIndex, Direction.RIGHT).ifTrue { count++ }
                    charMatrix.isXmas(rowIndex, colIndex, Direction.LEFT).ifTrue { count++ }
                    charMatrix.isXmas(rowIndex, colIndex, Direction.TOP_RIGHT).ifTrue { count++ }
                    charMatrix.isXmas(rowIndex, colIndex, Direction.TOP_LEFT).ifTrue { count++ }
                    charMatrix.isXmas(rowIndex, colIndex, Direction.BOTTOM_RIGHT).ifTrue { count++ }
                    charMatrix.isXmas(rowIndex, colIndex, Direction.BOTTOM_LEFT).ifTrue { count++ }
                }
            }
        }
        return count
    }

    private fun part2(): Int {
        val charMatrix = input.lines().map { line -> line.toList() }
        var count = 0
        for (rowIndex in 0..charMatrix.lastIndex) {
            for (colIndex in 0..charMatrix[rowIndex].lastIndex) {
                val char = charMatrix[rowIndex][colIndex]
                if (char == 'A' &&
                    (rowIndex - 1) >= 0 &&
                    (colIndex - 1) >= 0 &&
                    (rowIndex + 1) <= charMatrix.lastIndex &&
                    (colIndex + 1) <= charMatrix[rowIndex].lastIndex
                ) {
                    val topLeft = charMatrix[rowIndex - 1][colIndex - 1]
                    val topRight = charMatrix[rowIndex - 1][colIndex + 1]
                    val bottomRight = charMatrix[rowIndex + 1][colIndex + 1]
                    val bottomLeft = charMatrix[rowIndex + 1][colIndex - 1]

                    val isGoodLeftDiagonal =
                        (topLeft == 'M' && bottomRight == 'S') || (topLeft == 'S' && bottomRight == 'M')
                    val isGoodRightDiagonal =
                        (topRight == 'M' && bottomLeft == 'S') || (topRight == 'S' && bottomLeft == 'M')

                    if (isGoodRightDiagonal && isGoodLeftDiagonal) {
                        count++
                    }
                }
            }
        }

        return count
    }

    private fun Boolean.ifTrue(onXMAS: () -> Unit) {
        if (this) {
            onXMAS()
        }
    }

    private fun List<List<Char>>.isXmas(rowIndex: Int, colIndex: Int, direction: Direction): Boolean {
        val sb = StringBuilder()
        var rowX = rowIndex
        var colX = colIndex
        repeat(4) {
            if (rowX < 0 || colX < 0 || rowX > this.lastIndex || colX > this[rowX].lastIndex ) return false
            sb.append("${this[rowX][colX]}")
            rowX += direction.rowVal
            colX += direction.colVal
        }
        return sb.toString() == "XMAS"
    }
}