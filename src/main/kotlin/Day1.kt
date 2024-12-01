import kotlin.math.absoluteValue

interface Puzzle {
    fun solve(input: String): Pair<Int, Int>
}

class Day1 : Puzzle {
    override fun solve(input: String): Pair<Int, Int> {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()
        input.split("\n")
            .forEach { line ->
                line.split("   ").let {
                    leftList.add(it[0].toInt())
                    rightList.add(it[1].toInt())
                }
            }

        leftList.sort()
        rightList.sort()
        require(leftList.size == rightList.size)
        var diff = 0
        var similarityScore = 0
        repeat(leftList.size) { index ->
            val leftNum = leftList[index]
            diff += (leftNum - rightList[index]).absoluteValue
            similarityScore += (leftNum * rightList.count { it == leftNum })
        }

        return diff to similarityScore
    }

}