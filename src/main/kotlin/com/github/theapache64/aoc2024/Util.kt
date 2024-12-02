package com.github.theapache64.aoc2024

fun readInput(resName : String): String {
    return getResource("$resName.txt")!!.readText()
}

fun getResource(filename: String) = Thread.currentThread().contextClassLoader
    .getResourceAsStream(filename)
    ?.bufferedReader()