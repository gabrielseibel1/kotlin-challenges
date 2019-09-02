/**
 * Solves https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
 */

package lc1074

fun main() {
    val matrix = arrayOf(
            intArrayOf(1, 0),
            intArrayOf(0, 0)
    )
    println(numSubmatrixSumTarget(matrix, 1))
}

typealias Mat = Array<IntArray>
typealias Pos = Pair<Int, Int>

fun zeros(i: Int, j: Int): Mat = Array(i) { IntArray(j) { 0 } }

fun Mat.println() {
    forEach {
        println(it.joinToString())
    }
}

fun Mat.sub(start: Pos, end: Pos): Mat {
    val sub = zeros(end.first - start.first + 1, end.second - start.second + 1)
    for ((iSub, i) in (start.first..end.first).withIndex()) {
        for ((jSub, j) in (start.second..end.second).withIndex()) {
            sub[iSub][jSub] = this[i][j]
        }
    }
    return sub
}

fun Mat.nRows() = size

fun Mat.nCols() = this[0].size

fun Pos.plus(p: Pos) = Pos(first + p.first, second + p.second)

fun Pos.inside(g: Mat) = first in 0 until g.size && second in 0 until g[0].size

fun Mat.sum() = fold(0) { acc, it -> acc + it.fold(0) { a, el -> a + el } }

fun numSubmatrixSumTarget(m: Array<IntArray>, t: Int) = numSubmatrixSumTargetInN4(m,t)

fun numSubmatrixSumTargetInN4(m: Array<IntArray>, t: Int): Int {
    var count = 0

    for (ki in 0 until m.nRows()) {
        for (kj in 0 until m.nCols()) {

            for (i in 0 until m.nRows()) {
                for (j in 0 until m.nCols()) {
                    val start = Pos(i, j)
                    val end = Pos(i + ki, j + kj)

                    if (end.inside(m)) {
                        if (m.sub(start, end).sum() == t) count++
                    }
                }
            }
        }
    }

    return count
}