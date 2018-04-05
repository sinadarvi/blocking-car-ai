package com.darvishi.sina.unisearch

import java.util.ArrayList
import java.util.LinkedList
import java.util.Queue
import java.util.Stack

class IDS(internal var start: state, internal var end: state) {
    internal var path = ArrayList<String>()
    internal var time1: Long = 0
    internal var time2: Long = 0
    // ArrayList<state> VisitedList = new ArrayList<state>();
    internal var ChildList = ArrayList<state>()
    internal var fringe = Stack<state>()
    internal var find = false
    internal var current: state? = null
    internal var nodeCounter = 0
    internal var maxNodeCounter = 0
    internal var maxparentnumber: Int = 0
    internal var s: successor


    internal var co: state

    init {
        time1 = System.currentTimeMillis()
        start()


    }

    fun start() {
        var range = 0


        // VisitedList.add(start);
        outer@ while (true) {
            fringe.push(start)
            while (!fringe.isEmpty()) {
                current = fringe.pop()
                //    VisitedList.add(current);
                if (current!!.compare(end)) {
                    find = true
                    time2 = System.currentTimeMillis()
                    break@outer
                    // print(true);
                }
                ChildList.clear()
                if (current!!.depth < range) {
                    s = successor(current!!)
                    ChildList = s.GetSuccessors()
                } else {
                    ChildList = ArrayList()
                }
                for (i in ChildList) {
                    nodeCounter++
                    if (!isGenerated(i)) {
                        fringe.add(i)
                    }
                }
                if (fringe.size + maxparentnumber > maxNodeCounter) {
                    maxNodeCounter = fringe.size + maxparentnumber
                }

            }
            range++

        }

        if (!find) {
            print(false)
        } else {
            print(true)
        }
    }

    fun isGenerated(s: state): Boolean {
        var temp: state?
        temp = current//copy(current);
        maxparentnumber = 0
        while (temp!!.parent != null) {

            if (s.compare(temp)) {
                return true
            }
            maxparentnumber++

            temp = temp.parent

        }


        return false
    }


    fun print(result: Boolean) {
        println("IDS:")
        println()
        if (result) {

            while (current!!.parent != null) {
                path.add(current!!.PathFromParent)
                current = current!!.parent
            }
            println("action : " + path.size)
            println("Path:")
            for (i in path.indices.reversed()) {
                println(path[i])
            }

        } else {
            println("No Soloution")
        }
        println()
        // System.out.println("Max Parent Number :" + maxparentnumber);
        println("Number of gnarated nodes : " + (nodeCounter + 2))
        println("Max number of node in ram : " + (maxNodeCounter + 2))
        println("Time = " + (time2 - time1).toFloat() / 1000.toFloat() + " Sec")
        println()
    }

    private fun copy(s: state): state {
        co = state(null!!, s.row, s.column)
        co.array = Array(s.row) { CharArray(s.column) }
        co.parent = s.parent

        //        s = new state(null, curent.row, curent.column);
        //        s.array = new char[row][col];
        co.PathFromParent = s.PathFromParent
        for (i in 0 until s.row) {
            for (j in 0 until s.column) {
                co.array[i][j] = s.array[i][j]
            }
        }
        return co
    }


}