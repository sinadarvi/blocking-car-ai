package com.darvishi.sina.informed

import java.util.ArrayList
import java.util.LinkedList
import java.util.Queue
import java.util.Stack

class IDAstar(internal var start: state, internal var end: state) {
    internal var path = ArrayList<String>()
    internal var time1: Long = 0
    internal var time2: Long = 0
    // ArrayList<state> VisitedList = new ArrayList<state>();
    internal var ChildList = ArrayList<state>()
    internal var fringe = Stack<state>()
    internal var find = false
    internal var current: state? = null
    internal var totalcost: Int = 0
    internal var nodeCounter = 0
    internal var maxNodeCounter = 0
    internal var maxparentnumber: Int = 0
    internal var cost_range = 0
    internal var minCost = 0
    internal lateinit var s: successor


    internal lateinit var co: state

    init {
        time1 = System.currentTimeMillis()
        start()


    }

    fun start() {

        // VisitedList.add(start);
        outer@ while (true) {
            fringe.push(start)
            while (!fringe.isEmpty()) {
                current = fringe.pop()
                //    VisitedList.add(current);
                if (current!!.compare(end)) {
                    totalcost = current!!.CostFromRoot
                    find = true
                    time2 = System.currentTimeMillis()
                    break@outer
                    // print(true);
                }

                ChildList.clear()

                if (current!!.CostFromRoot + current!!.h < cost_range) {
                    s = successor(current!!)
                    s.set_goal(end)
                    ChildList = s.GetSuccessors()
                } else {
                    ChildList = ArrayList()
                }
                for (i in ChildList) {
                    nodeCounter++
                    if (!isGenerated(i)) {
                        fringe.push(i)
                        if (i.CostFromRoot + i.h < minCost) {

                            minCost = i.CostFromRoot + i.h
                        }
                    }
                }

                if (cost_range == 0) {
                    current!!.calculate_H(end)
                    cost_range = current!!.CostFromRoot + current!!.h + 1
                }
                if (fringe.size + maxparentnumber > maxNodeCounter) {
                    maxNodeCounter = fringe.size + maxparentnumber
                }

            }
            if (!find && minCost != 0) {
                cost_range = minCost
                //visited.clear();
                ChildList.clear()
                fringe.clear()
            }

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
        println("IDAstar:")
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
        println("Total Cost : $totalcost")
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