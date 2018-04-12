package com.darvishi.sina.informed

import java.util.ArrayList
import java.util.Comparator
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

class GBFS(internal var start: state, internal var end: state) {
    internal var path = ArrayList<String>()
    internal var time1: Long = 0
    internal var time2: Long = 0
    internal var VisitedList = ArrayList<state>()
    internal var ChildList = ArrayList<state>()
    internal var find = false
    internal var current: state? = null
    internal var nodeCounter = 0
    internal var maxNodeCounter = 0
    internal var totalcost: Int = 0
    internal lateinit var s: successor

    init {
        time1 = System.currentTimeMillis()
        start()


    }

    fun start() {

        val fringe = PriorityQueue(10000, Comparator<state> { n1, n2 ->
            if (n1.h > n2.h) {
                1
            } else if (n1.h < n2.h) {
                -1
            } else {
                0
            }
        })

        fringe.add(start)
        VisitedList.add(start)
        while (!fringe.isEmpty()) {
            current = fringe.poll()
            VisitedList.add(current!!)
            if (current!!.compare(end)) {
                totalcost = current!!.CostFromRoot
                time2 = System.currentTimeMillis()
                find = true
                break
                // print(true);
            }
            s = successor(current!!)
            ChildList.clear()
            s.set_goal(end)
            ChildList = s.GetSuccessors()
            for (i in ChildList) {
                nodeCounter++
                if (!isGenerated(i)) {
                    fringe.add(i)

                }
            }
            if (fringe.size + VisitedList.size > maxNodeCounter) {
                maxNodeCounter = fringe.size + VisitedList.size
            }

        }

        if (!find) {
            print(false)
        } else {
            print(true)
        }
    }

    fun isGenerated(s: state): Boolean {

        for (t in VisitedList) {
            if (s.compare(t)) {
                // System.out.println("yess");
                return true

            }
        }
        return false
    }


    fun print(result: Boolean) {
        println("GBFS:")
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
        println("Number of gnarated nodes : " + (nodeCounter + 2))
        println("Max number of node in ram : " + (maxNodeCounter + 2))
        println("Time = " + (time2 - time1).toFloat() / 1000.toFloat() + " Sec")
    }

}