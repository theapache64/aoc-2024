package com.github.theapache64.aoc2024

fun main(args: Array<String>) {
    Day2().setupAndStart()
}

class Day2 : Puzzle() {
    private var isDecreasing: Boolean? = null
    override fun solve(): Pair<Int, Int> {
        var normalSafeReports = 0
        var noOfToleratedSafeReports = 0
        input.lines().forEach { line ->
            val report = line.split(" ").map { it.toInt() }
            val isNormalSafeReport = report.isSafe()
            if (isNormalSafeReport) {
                normalSafeReports++
            } else {
                for (index in 0..report.lastIndex) {
                    val reportCopy = report.toMutableList().apply { removeAt(index) }
                    if (reportCopy.isSafe()) {
                        noOfToleratedSafeReports++
                        break
                    }
                }
            }
        }

        return normalSafeReports to (noOfToleratedSafeReports + normalSafeReports)
    }

    private fun List<Int>.isSafe(): Boolean {
        isDecreasing = null
        val adjPairs = this.windowed(2)
        for ((x, y) in adjPairs) {
            if (!isSafe(x, y)) {
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