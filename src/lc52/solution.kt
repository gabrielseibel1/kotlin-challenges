/**
 * Solves https://leetcode.com/problems/n-queens-ii/submissions/
 */

package lc52

import kotlin.math.ceil

fun main() {
    println(totalNQueens(4))
}

fun totalNQueens(n: Int): Int {
    val cols = mutableSetOf<Int>()
    val diagsP = mutableSetOf<Int>()
    val diagsN = mutableSetOf<Int>()

    return ceil(nQueens(n, 0, cols, diagsP, diagsN).toDouble() / 2).toInt()
}

fun nQueens(n: Int, i: Int, cols: MutableSet<Int>, diagsP: MutableSet<Int>, diagsN: MutableSet<Int>): Int {

    var nQ = 0

    for (j in 0 until n) {
        if (allowed(i, j, cols, diagsP, diagsN)) {

            if (i == n - 1) nQ++
            else  {
                add(i, j, cols, diagsP, diagsN)
                nQ += nQueens(n, i + 1, cols, diagsP, diagsN)
                remove(i, j, cols, diagsP, diagsN)
            }
        }
    }

    return nQ
}


fun add(i: Int, j: Int, cols: MutableSet<Int>, diagsP: MutableSet<Int>, diagsN: MutableSet<Int>) {
    cols.add(j)
    diagsP.add(diagP(i, j))
    diagsN.add(diagN(i, j))
}

fun remove(i: Int, j: Int, cols: MutableSet<Int>, diagsP: MutableSet<Int>, diagsN: MutableSet<Int>) {
    cols.remove(j)
    diagsP.remove(diagP(i, j))
    diagsN.remove(diagN(i, j))
}

fun diagP(i: Int, j: Int): Int = i + j

fun diagN(i: Int, j: Int): Int = j - i

fun allowed(i: Int, j: Int, cols: Set<Int>, diagsP: Set<Int>, diagsN: Set<Int>) =
        !cols.contains(j) && !diagsP.contains(diagP(i, j)) && !diagsN.contains(diagN(i, j))