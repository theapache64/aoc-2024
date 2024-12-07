package com.github.theapache64.aoc2024

import kotlinx.benchmark.*

@State(Scope.Benchmark)
class DayBenchmark {
    private final val day = Day4()

    @Setup
    fun prepare() {
        day.input = readInput(day::class.simpleName!!)
    }

    @Benchmark
    fun solution(): Pair<Int, Int> {
        return day.solve()
    }
}