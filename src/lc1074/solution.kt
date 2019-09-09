/**
 * Solves https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
 */

package lc1074

fun main() {
    val matrix = arrayOf(
            intArrayOf(1, -1),
            intArrayOf(-1, 1)
    )
    /*val matrix = arrayOf(
            intArrayOf(0, 1, 0),
            intArrayOf(1, 1, 1),
            intArrayOf(0, 1, 0)
    )*/
    println(numSubmatrixSumTarget(matrix, 0))
}

typealias Mat = Array<IntArray>

fun Mat.println() {
    forEach {
        println(it.joinToString())
    }
}

fun Mat.nRows() = size

fun Mat.nCols() = this[0].size

fun numSubmatrixSumTarget(m: Array<IntArray>, t: Int): Int {
    var count = 0
    val lastRow = IntArray(m.nCols()) { 0 }

    for (i in 0 until m.nRows()) {
        for (j in 0 until m.nCols()) {

            //clear lastRow
            for (lri in 0 until m.nCols() - j) {
                lastRow[lri] = 0
            }

            //use lastRow and row running sum
            for (ki in 0 until m.nRows() - i) {
                var sum = 0
                for (kj in 0 until m.nCols() - j) {
                    sum += m[i + ki][j + kj]
                    lastRow[kj] = sum + lastRow[kj]
                    if (lastRow[kj] == t) count++
                }
            }
        }
    }

    return count
}