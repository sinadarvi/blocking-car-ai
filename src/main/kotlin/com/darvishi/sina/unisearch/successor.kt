//package com.darvishi.sina.unisearch
//
//import java.util.ArrayList
//
////import sun.org.mozilla.javascript.internal.ast.Jump;
//
//class successor(private val curent: state) {
//    private val list = ArrayList<state>()
//    private val temp: state
//    internal var row: Int = 0
//    internal var col: Int = 0
//    private val fullpoint: Array<IntArray>? = null
//
//
//    internal var co: state? = null
//
//    init {
//        this.row = curent.row
//        this.col = curent.column
//
//        temp = this!!.copy(curent)!!
//    }
//
//    fun GetSuccessors(): ArrayList<state> {
//        row = curent.row
//        col = curent.column
//
//        this.add()
//        this.remove()
//        this.move2()
//        this.move1()
//
//
//        val it = list.iterator()
//        while (it.hasNext()) {
//            it.next().parent = curent
//        }
//        return list
//    }
//
//    private fun move2() {
//        for (i in 0 until row) {
//            for (j in 0 until col) {
//                if (curent.array[i][j] == '#') {
//                    temp.array[i][j] = '.'
//                    if (checkexist(i - 2, j)) {
//                        if (checksafe(i - 2, j)) {
//                            temp.array[i - 2][j] = '#'
//                            temp.PathFromParent = "move ( " + i + " , " + j + " ) to ( " + (i - 2) + " , " + j + ")"
//                            temp.PathFromEnd = "move ( " + (i - 2) + " , " + j + " ) to ( " + i + " , " + j + ")"
//                            temp.CostFromRoot = curent.CostFromRoot + 2
//                            temp.depth = curent.depth + 1
//                            list.add(this!!.copy(temp)!!)
//                            temp.array[i - 2][j] = '.'
//                        }
//                    }
//                    if (checkexist(i + 2, j)) {
//                        if (checksafe(i + 2, j)) {
//                            temp.array[i + 2][j] = '#'
//                            temp.PathFromParent = "move ( " + i + " , " + j + " ) to ( " + (i + 2) + " , " + j + ")"
//                            temp.PathFromEnd = "move ( " + (i + 2) + " , " + j + " ) to ( " + i + " , " + j + ")"
//                            temp.CostFromRoot = curent.CostFromRoot + 2
//                            temp.depth = curent.depth + 1
//                            list.add(this!!.copy(temp)!!)
//                            temp.array[i + 2][j] = '.'
//                        }
//                    }
//                    if (checkexist(i, j - 2)) {
//                        if (checksafe(i, j - 2)) {
//                            temp.array[i][j - 2] = '#'
//                            temp.PathFromParent = "move ( " + i + " , " + j + " ) to ( " + i + " , " + (j - 2) + ")"
//                            temp.PathFromEnd = "move ( " + i + " , " + (j - 2) + " ) to ( " + i + " , " + j + ")"
//                            temp.CostFromRoot = curent.CostFromRoot + 2
//                            temp.depth = curent.depth + 1
//                            list.add(this!!.copy(temp)!!)
//                            temp.array[i][j - 2] = '.'
//                        }
//                    }
//                    if (checkexist(i, j + 2)) {
//                        if (checksafe(i, j + 2)) {
//                            temp.array[i][j + 2] = '#'
//                            temp.PathFromParent = "move ( " + i + " , " + j + " ) to ( " + i + " , " + (j + 2) + ")"
//                            temp.PathFromEnd = "move ( " + i + " , " + (j + 2) + " ) to ( " + i + " , " + j + ")"
//                            temp.CostFromRoot = curent.CostFromRoot + 2
//                            temp.depth = curent.depth + 1
//                            list.add(this!!.copy(temp)!!)
//                            temp.array[i][j + 2] = '.'
//                        }
//                    }
//                    temp.array[i][j] = '#'
//                }
//            }
//        }
//    }
//
//
//    private fun move1() {
//        for (i in 0 until row) {
//            for (j in 0 until col) {
//                if (curent.array[i][j] == '#') {
//                    curent.array[i][j] = '.'
//                    temp.array[i][j] = '.'
//
//                    //=========================
//                    if (checkexist(i - 1, j)) {
//                        if (checksafe(i - 1, j)) {
//                            temp.array[i - 1][j] = '#'
//                            temp.PathFromParent = "move ( " + i + " , " + j + " ) to ( " + (i - 1) + " , " + j + ")"
//                            temp.PathFromEnd = "move ( " + (i - 1) + " , " + j + " ) to ( " + i + " , " + j + ")"
//                            temp.CostFromRoot = curent.CostFromRoot + 1
//                            temp.depth = curent.depth + 1
//                            list.add(this!!.copy(temp)!!)
//                            temp.array[i - 1][j] = '.'
//                        }
//                    }
//                    if (checkexist(i + 1, j)) {
//                        if (checksafe(i + 1, j)) {
//                            temp.array[i + 1][j] = '#'
//                            temp.PathFromParent = "move ( " + i + " , " + j + " ) to ( " + (i + 1) + " , " + j + ")"
//                            temp.PathFromEnd = "move ( " + (i + 1) + " , " + j + " ) to ( " + i + " , " + j + ")"
//                            temp.CostFromRoot = curent.CostFromRoot + 1
//                            temp.depth = curent.depth + 1
//                            list.add(this!!.copy(temp)!!)
//                            temp.array[i + 1][j] = '.'
//                        }
//                    }
//                    if (checkexist(i, j - 1)) {
//                        if (checksafe(i, j - 1)) {
//                            temp.array[i][j - 1] = '#'
//                            temp.PathFromParent = "move ( " + i + " , " + j + " ) to ( " + i + " , " + (j - 1) + ")"
//                            temp.PathFromEnd = "move ( " + i + " , " + (j - 1) + " ) to ( " + i + " , " + j + ")"
//                            temp.CostFromRoot = curent.CostFromRoot + 1
//                            temp.depth = curent.depth + 1
//                            list.add(this!!.copy(temp)!!)
//                            temp.array[i][j - 1] = '.'
//                        }
//                    }
//                    if (checkexist(i, j + 1)) {
//                        if (checksafe(i, j + 1)) {
//                            temp.array[i][j + 1] = '#'
//                            temp.PathFromParent = "move ( " + i + " , " + j + " ) to ( " + i + " , " + (j + 1) + ")"
//                            temp.PathFromEnd = "move ( " + i + " , " + (j + 1) + " ) to ( " + i + " , " + j + ")"
//                            temp.CostFromRoot = curent.CostFromRoot + 1
//                            temp.depth = curent.depth + 1
//                            list.add(this!!.copy(temp)!!)
//                            temp.array[i][j + 1] = '.'
//                        }
//                    }
//
//                    //=================================
//
//                    curent.array[i][j] = '#'
//                    temp.array[i][j] = '#'
//                }
//            }
//        }
//
//    }
//
//    private fun remove() {
//        for (i in 0 until row) {
//            for (j in 0 until col) {
//                if (curent.array[i][j] == '#') {
//                    temp.array[i][j] = '.'
//                    temp.PathFromParent = "remove ( $i , $j ) "
//                    temp.PathFromEnd = "add ( $i , $j ) "
//                    temp.CostFromRoot = curent.CostFromRoot + Math.max(row, col)
//                    temp.depth = curent.depth + 1
//                    list.add(this!!.copy(temp)!!)
//                    temp.array[i][j] = '#'
//                }
//            }
//        }
//
//    }
//
//    private fun add() {
//        for (i in 0 until row) {
//            for (j in 0 until col) {
//                if (curent.array[i][j] == '.' && checksafe(i, j)) {
//                    temp.array[i][j] = '#'
//                    temp.PathFromParent = "add ( $i , $j ) "
//                    temp.PathFromEnd = "add ( $i , $j ) "
//                    temp.CostFromRoot = curent.CostFromRoot + Math.max(row, col)
//                    temp.depth = curent.depth + 1
//                    list.add(this!!.copy(temp)!!)
//                    temp.array[i][j] = '.'
//                }
//            }
//        }
//
//    }
//
//    fun checkexist(i: Int, j: Int): Boolean {
//        return i >= 0 && i < row && j >= 0 && j < col
//    }
//
//    private fun checksafe(i: Int, j: Int): Boolean {
//        if (curent.array[i][j] != '#') {
//
//            //    corner points :
//            if (i == 0 && j == 0) {
//                if (curent.array[i + 1][j] != '#' && curent.array[i][j + 1] != '#' && curent.array[i + 1][j + 1] != '#') {
//                    return true
//                }
//            }
//            if (i == 0 && j == col - 1) {
//                if (curent.array[i + 1][j] != '#' && curent.array[i][j - 1] != '#' && curent.array[i + 1][j - 1] != '#') {
//                    return true
//                }
//            }
//            if (i == row - 1 && j == 0) {
//                if (curent.array[i][j + 1] != '#' && curent.array[i - 1][j] != '#' && curent.array[i - 1][j + 1] != '#') {
//                    return true
//                }
//            }
//            if (i == row - 1 && j == col - 1) {
//                if (curent.array[i - 1][j] != '#' && curent.array[i][j - 1] != '#' && curent.array[i - 1][j - 1] != '#') {
//                    return true
//                }
//            }
//            // one line points  :
//            if (i == 0 && 0 < j && j < col - 1) {
//                if (curent.array[i][j - 1] != '#' && curent.array[i + 1][j - 1] != '#' && curent.array[i + 1][j] != '#' && curent.array[i + 1][j + 1] != '#' && curent.array[i][j + 1] != '#') {
//                    return true
//                }
//            }
//            if (i == row - 1 && 0 < j && j < col - 1) {
//                if (curent.array[i][j - 1] != '#' && curent.array[i - 1][j - 1] != '#' && curent.array[i - 1][j] != '#' && curent.array[i - 1][j + 1] != '#' && curent.array[i][j + 1] != '#') {
//                    return true
//                }
//            }
//            if (j == 0 && 0 < i && i < row - 1) {
//                if (curent.array[i - 1][j] != '#' && curent.array[i - 1][j + 1] != '#' && curent.array[i][j + 1] != '#' && curent.array[i + 1][j + 1] != '#' && curent.array[i + 1][j] != '#') {
//                    return true
//                }
//            }
//            if (j == col - 1 && 0 < i && i < row - 1) {
//                if (curent.array[i - 1][j] != '#' && curent.array[i - 1][j - 1] != '#' && curent.array[i][j - 1] != '#' && curent.array[i + 1][j - 1] != '#' && curent.array[i + 1][j] != '#') {
//                    return true
//                }
//            }
//            // middle points :
//            if (i > 0 && i < row - 1 && j > 0 && j < col - 1) {
//                if (curent.array[i - 1][j - 1] != '#' && curent.array[i - 1][j] != '#'
//                        && curent.array[i - 1][j + 1] != '#' && curent.array[i][j - 1] != '#'
//                        && curent.array[i][j + 1] != '#' && curent.array[i + 1][j - 1] != '#'
//                        && curent.array[i + 1][j] != '#' && curent.array[i + 1][j + 1] != '#') {
//                    return true
//                }
//            }
//        }
//        return false
//    }
//
//    fun printlist() {
//        println("print:")
//        println(list.size)
//        for (l in list.indices) {
//            println("L :$l")
//            println("path : " + list[l].PathFromParent)
//            for (i in 0 until row) {
//                for (j in 0 until col) {
//                    print(list[l].array[i][j])
//                }
//                println()
//            }
//        }
//    }
//
//    fun printstate() {
//        println("curent :")
//        for (i in 0 until row) {
//            for (j in 0 until col) {
//                print(curent.array[i][j])
//            }
//            println()
//        }
//        println("temp :")
//        for (i in 0 until row) {
//            for (j in 0 until col) {
//                print(temp.array[i][j])
//            }
//            println()
//        }
//    }
//
//    fun print(s: state) {
//        for (i in 0 until row) {
//            for (j in 0 until col) {
//                print(s.array[i][j])
//            }
//            println()
//        }
//
//    }
//
//    private fun copy(s: state): state? {
//        co = state(null!!, curent.row, curent.column)
//        co?.array = Array(row) { CharArray(col) }
//        //        s = new state(null, curent.row, curent.column);
//        //        s.array = new char[row][col];
//        co?.PathFromParent = s.PathFromParent
//        co?.PathFromEnd = s.PathFromEnd
//        co?.CostFromRoot = s.CostFromRoot
//        co?.depth = s.depth
//        for (i in 0 until row) {
//            for (j in 0 until col) {
//                co?.array?.get(i)!![j] = s.array[i][j]
//            }
//        }
//        return co
//    }
//
//}
