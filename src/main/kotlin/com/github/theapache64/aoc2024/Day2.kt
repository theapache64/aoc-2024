package com.github.theapache64.aoc2024

fun main(args: Array<String>) {
    Day2().setupAndStart()
}

class Day2 : Puzzle() {
    private var isDecreasing: Boolean? = null
    private var errorReports = mutableListOf<List<Int>>()

    override fun solve(): Pair<Int, Int> {
        val noOfSafeReports = input.lines().map { report ->
            report.split(" ").map { it.toInt() }.isSafe(shouldAddToReport = true)
        }.count { isSafe -> isSafe }

        var noOfToleratedSafeReports = 0
        for (errorReport in errorReports) {
            val copyErrorReport = errorReport.toMutableList()
            nestedLoop@ for ((index, removeElement) in errorReport.withIndex()) {
                copyErrorReport.removeAt(index)
                if (copyErrorReport.isSafe(shouldAddToReport = false)) {
                    noOfToleratedSafeReports++
                    break@nestedLoop
                } else {
                    copyErrorReport.add(index, removeElement)
                }
            }
        }

        return noOfSafeReports to (noOfToleratedSafeReports + noOfSafeReports)
    }

    private fun List<Int>.isSafe(shouldAddToReport: Boolean): Boolean {
        isDecreasing = null
        val adjPairs = this.windowed(2)
        for (pair in adjPairs) {
            val (x, y) = pair
            val isSafe = isSafe(x, y)
            if (!isSafe) {
                if (shouldAddToReport) {
                    errorReports.add(this)
                }
                return false
            }
        }
        return true
    }

    private fun isSafe(x: Int, y: Int): Boolean {
        val diff = x - y
        if (diff == 0) return false
        if (isDecreasing == null) isDecreasing = diff < 0
        if (isDecreasing == true && diff >= 0) return false
        if (isDecreasing == false && diff <= 0) return false
        if (diff > 3 || diff < -3) return false
        return true
    }

}