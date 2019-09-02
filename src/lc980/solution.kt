/**
 * Solves https://leetcode.com/problems/unique-paths-iii/
 */

package lc980

import java.util.Stack

typealias Grid = Array<IntArray>
typealias Position = Pair<Int, Int>

const val FLOOR = 0
const val START = 1
const val END = 2
const val LAVA = -1

fun main() {
    val grid = arrayOf(
            intArrayOf(1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 0, 2, -1)
    )
    println("\nGrid:")
    grid.print()

    println("\nPaths:")
    println(paths(grid, grid.floors(), Stack(), grid.start(), 0))
}

/**
 * DFS
 */
fun paths(g: Grid, floors: Int, s: Stack<Position>, p: Position, paths: Int): Int {
    s.push(p)
    return when (g.at(p)) {
        END -> (s.count { g.at(it) == FLOOR } == floors).toInt()
        else -> arrayOf(p.right(), p.left(), p.down(), p.up())
                .filter { it.inside(g) && !s.contains(it) && g.at(it) != LAVA }
                .fold(paths) { acc, child -> acc + paths(g, floors, s, child, paths) }
    }.also { s.pop() }
}

fun Grid.print() {
    forEach {
        println(it.joinToString())
    }
}

fun Grid.start(): Position {
    forEachIndexed { i, row ->
        row.forEachIndexed { j, elem ->
            if (elem == START) return Position(i, j)
        }
    }
    throw Exception("no start in $this")
}

fun Grid.floors(): Int = fold(0) { acc, arr -> acc + arr.count { it == FLOOR } }

fun Grid.at(p: Position) = this[p.first][p.second]

fun Position.right() = Position(first + 1, second)

fun Position.left() = Position(first - 1, second)

fun Position.down() = Position(first, second + 1)

fun Position.up() = Position(first, second - 1)

fun Position.inside(g: Grid) = first in 0 until g.size && second in 0 until g[0].size

fun Boolean.toInt() = if (this) 1 else 0

