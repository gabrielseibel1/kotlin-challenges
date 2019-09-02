/**
 * Solves https://leetcode.com/problems/max-increase-to-keep-city-skyline/
 */

package lc807

import kotlin.math.min

fun main() {
    val grid = arrayOf(
            intArrayOf(3, 0, 8, 4),
            intArrayOf(2, 4, 5, 7),
            intArrayOf(9, 2, 6, 3),
            intArrayOf(0, 3, 1, 0)
    )
    println("SUM = ${maxIncreaseKeepingSkyline(grid)}")
}

fun maxIncreaseKeepingSkyline(grid: Array<IntArray>): Int {

    println("\nSKYLINES\n")
    val ver = skylineUD(grid).also { it.println() }
    val hor = skylineLR(grid).also { it.println() }

    println("\nNEW GRID\n")
    var sum = 0
    val newGrid = zeros(grid.size)
    for (i in 0 until grid.size) {
        for (j in 0 until grid.size) {
            val new = min(hor[i], ver[j])
            sum += new - grid[i][j]
            newGrid[i][j] = new
        }
    }
    newGrid.println()

    return sum
}

fun skylineLR(rows: Array<IntArray>) = IntArray(rows.size) { rows[it].max()!! }

fun skylineUD(rows: Array<IntArray>): IntArray {
    val cols = rows.transposed()
    return IntArray(cols.size) { cols[it].max()!! }
}

fun zeros(size: Int) = Array(size) { IntArray(size) { 0 } }

fun IntArray.println() {
    println(joinToString())
}

fun Array<IntArray>.println() {
    forEach { it.println() }
}

fun Array<IntArray>.transposed() =
        Array(size) { i ->
            map { it[i] }.toIntArray()
        }
