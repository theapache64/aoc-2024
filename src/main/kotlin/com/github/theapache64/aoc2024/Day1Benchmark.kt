package com.github.theapache64.aoc2024

import kotlinx.benchmark.*

@State(Scope.Benchmark)
class Day1Benchmark {
    private final val day = Day1()

    @Setup
    fun prepare() {
        day.input = readInput(day::class.simpleName!!)
    }

    @Benchmark
    fun firstSolution(): Pair<Int, Int> {
        return day.solve()
    }

    @Benchmark
    fun zipSolution() : Pair<Int, Int> {
        return day.solveUsingZip()
    }

    @Benchmark
    fun zip2Solution() : Pair<Int, Int> {
        return day.solveUsingZip2()
    }

    @Benchmark
    fun substringSolution() : Pair<Int, Int> {
        return day.solveUsingSubstring()
    }
}