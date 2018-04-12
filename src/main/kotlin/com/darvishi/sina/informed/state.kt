package com.darvishi.sina.informed

class state(var array: Array<CharArray>, internal var row: Int, internal var column: Int) : Comparable<state> {
    var parent: state? = null
    internal var PathFromParent = " "
    internal var PathFromEnd = " "
    internal var CostFromRoot: Int = 0
    internal var h: Int = 0
    internal var depth: Int = 0

    fun compare(s: state): Boolean {
        for (i in 0 until s.row) {
            for (j in 0 until s.column) {
                if (s.array[i][j] != this.array[i][j]) {
                    return false
                }
            }

        }
        return true
    }

    fun print() {
        for (i in 0 until row) {
            for (j in 0 until column) {
                print(array[i][j])
            }
            println()
        }
    }

    fun calculate_H(end: state) {
        var h = 0
        for (i in 0 until row) {
            for (j in 0 until column) {
                if (this.array[i][j] != end.array[i][j])
                    h++
            }
        }
        this.h = h
    }

    override fun compareTo(o: state): Int {
        return this.CostFromRoot - o.CostFromRoot
    }
}
