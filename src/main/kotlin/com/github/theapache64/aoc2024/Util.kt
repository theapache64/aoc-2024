package com.github.theapache64.aoc2024
fun getResource(filename: String) = Thread.currentThread().contextClassLoader
    .getResourceAsStream(filename)
    ?.bufferedReader()