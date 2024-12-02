package com.github.theapache64.aoc2024

import kotlinx.benchmark.*

@State(Scope.Benchmark)
class Day1Benchmark {
    private final val day = Day1()

    @Setup
    fun prepare() {
        day.setInput(readInput(day::class.simpleName!!))
    }

    @Benchmark
    fun benchmarkMethod(): Pair<Int, Int> {
        return day.solve()
    }
}