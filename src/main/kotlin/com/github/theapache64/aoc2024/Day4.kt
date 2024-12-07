package com.github.theapache64.aoc2024

fun main(args: Array<String>) {
    Day4().setupAndStart()
}

enum class Direction {
    TOP,
    BOTTOM,
    LEFT,
    RIGHT,
    BOTTOM_RIGHT,
    BOTTOM_
}

class Day4 : Puzzle() {
    override fun solve(): Pair<Int, Int> {
        return part1() to part2()
    }

    private fun part1(): Int {
        val charMatrix = input.lines().map { line ->
            line.toList()
        }
        println("QuickTag: Day4:part1: -----------------------")
        for ((rowIndex, row) in charMatrix.withIndex()) {
            for ((colIndex, col) in row.withIndex()) {
                print("[($rowIndex, $colIndex) ${charMatrix[rowIndex][colIndex]}]   ")
            }
            println()
        }
        println("QuickTag: Day4:part1: -----------------------")

        var count = 0
        for (i in 0..charMatrix.lastIndex) {
            for (j in 0..charMatrix[i].lastIndex) {
                val char = charMatrix[i][j]
                if (char == 'X') {
                    charMatrix.rightWord(i, j).onXMAS { count++ }
                    charMatrix.leftWord(i, j).onXMAS { count++ }
                    charMatrix.topWord(i, j).onXMAS { count++ }
                    charMatrix.bottomWord(i, j).onXMAS { count++ }
                    charMatrix.topRightWord(i, j).onXMAS { count++ }
                    charMatrix.topLeftWord(i, j).onXMAS { count++ }
                    charMatrix.bottomLeftWord(i, j).onXMAS { count++ }
                    charMatrix.bottomRightWord(i, j).onXMAS { count++ }
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
                    } catch (e: Exception) {
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

    private fun List<List<Char>>.rightWord(rowIndex: Int, colIndex: Int): String {
        return try {
            val c1 = this[rowIndex][colIndex]
            val c2 = this[rowIndex][colIndex + 1]
            val c3 = this[rowIndex][colIndex + 2]
            val c4 = this[rowIndex][colIndex + 3]
            if ("${c1}${c2}${c3}${c4}" == "XMAS") {
                "XMAS"
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }

    private fun List<List<Char>>.leftWord(rowIndex: Int, colIndex: Int): String {
        return try {
            val c1 = this[rowIndex][colIndex]
            val c2 = this[rowIndex][colIndex - 1]
            val c3 = this[rowIndex][colIndex - 2]
            val c4 = this[rowIndex][colIndex - 3]
            if ("${c1}${c2}${c3}${c4}" == "XMAS") {
                "XMAS"
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }

    private fun List<List<Char>>.topWord(rowIndex: Int, colIndex: Int): String {
        return try {
            val c1 = this[rowIndex][colIndex]
            val c2 = this[rowIndex - 1][colIndex]
            val c3 = this[rowIndex - 2][colIndex]
            val c4 = this[rowIndex - 3][colIndex]
            if ("${c1}${c2}${c3}${c4}" == "XMAS") {
                "XMAS"
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }


    private fun List<List<Char>>.bottomWord(rowIndex: Int, colIndex: Int): String {
        return try {
            val c1 = this[rowIndex][colIndex]
            val c2 = this[rowIndex + 1][colIndex]
            val c3 = this[rowIndex + 2][colIndex]
            val c4 = this[rowIndex + 3][colIndex]
            if ("${c1}${c2}${c3}${c4}" == "XMAS") {
                "XMAS"
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }

    private fun List<List<Char>>.topRightWord(rowIndex: Int, colIndex: Int): String {
        return try {
            val c1 = this[rowIndex][colIndex]
            val c2 = this[rowIndex - 1][colIndex + 1]
            val c3 = this[rowIndex - 2][colIndex + 2]
            val c4 = this[rowIndex - 3][colIndex + 3]
            if ("${c1}${c2}${c3}${c4}" == "XMAS") {
                "XMAS"
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }

    private fun List<List<Char>>.topLeftWord(rowIndex: Int, colIndex: Int): String {
        return try {
            val c1 = this[rowIndex][colIndex]
            val c2 = this[rowIndex - 1][colIndex - 1]
            val c3 = this[rowIndex - 2][colIndex - 2]
            val c4 = this[rowIndex - 3][colIndex - 3]
            if ("${c1}${c2}${c3}${c4}" == "XMAS") {
                println("QuickTag: Day4:topLeftWord: at $rowIndex, $colIndex")
                "XMAS"
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }

    private fun List<List<Char>>.bottomRightWord(rowIndex: Int, colIndex: Int): String {
        return try {
            val c1 = this[rowIndex][colIndex]
            val c2 = this[rowIndex + 1][colIndex + 1]
            val c3 = this[rowIndex + 2][colIndex + 2]
            val c4 = this[rowIndex + 3][colIndex + 3]
            if ("${c1}${c2}${c3}${c4}" == "XMAS") {
                "XMAS"
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }

    private fun List<List<Char>>.bottomLeftWord(rowIndex: Int, colIndex: Int): String {
        return try {
            val c1 = this[rowIndex][colIndex]
            val c2 = this[rowIndex + 1][colIndex - 1]
            val c3 = this[rowIndex + 2][colIndex - 2]
            val c4 = this[rowIndex + 3][colIndex - 3]
            if ("${c1}${c2}${c3}${c4}" == "XMAS") {
                "XMAS"
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }

}