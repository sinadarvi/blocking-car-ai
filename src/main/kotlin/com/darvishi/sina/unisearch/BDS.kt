package com.darvishi.sina.unisearch

import java.util.ArrayList
import java.util.LinkedList
import java.util.Queue

class BDS(internal var start: state, internal var end: state) {

    internal var path1 = ArrayList<String>()
    internal var path2 = ArrayList<String>()

    internal var time = System.currentTimeMillis()

    internal var VisitedList1 = ArrayList<state>()
    internal var VisitedList2 = ArrayList<state>()

    internal var ChildList1 = ArrayList<state>()
    internal var ChildList2 = ArrayList<state>()

    internal var fringe1: Queue<state> = LinkedList()
    internal var fringe2: Queue<state> = LinkedList()

    internal var successor1: successor
    internal var successor2: successor

    internal var depth1: Int = 0
    internal var depth2: Int = 0

    internal var find = false

    internal var nodeCounter = 0
    internal var maxNodeCounter = 0
    internal var mid1: state? = null
    internal var mid2: state? = null


    init {

        mid1 = start
        mid2 = end
        start()


    }

    fun start() {


        fringe1.add(start)
        fringe2.add(end)
        //  VisitedList.add(start);
        while (true) {

            //compare
            if (CheckFring()) {
                find = true
                break
            }
            //=======================================================================================

            while (mid1!!.depth == depth1) {
                mid1 = fringe1.poll()
                VisitedList1.add(mid1)
                successor1 = successor(mid1!!)
                ChildList1 = successor1.GetSuccessors()
                nodeCounter += ChildList1.size

                for (i in ChildList1) {
                    if (!isGenerated1(i)) {
                        fringe1.add(i)
                    }
                }
                ChildList1.clear()
            }


            depth1++
            //=================================================================================
            //compare
            if (CheckFring()) {
                find = true
                break
            }
            //=================================================================================
            ChildList2.clear()
            while (mid2!!.depth == depth2) {
                mid2 = fringe2.poll()
                VisitedList2.add(mid2)
                successor2 = successor(mid2!!)
                ChildList2 = successor2.GetSuccessors()
                nodeCounter += ChildList2.size

                for (i in ChildList2) {
                    if (!isGenerated1(i)) {
                        fringe2.add(i)
                    }
                }
                ChildList2.clear()

            }

            depth2++

            //======================================================================================
            //nodeCounter += ChildList1.size()+ChildList2.size();

            if (fringe1.size + fringe2.size + VisitedList1.size + VisitedList2.size > maxNodeCounter) {
                maxNodeCounter = fringe1.size + fringe2.size + VisitedList1.size + VisitedList2.size
            }

        }

        if (!find) {
            print(false)
        } else {
            print(true)
        }
    }

    fun isGenerated1(s: state): Boolean {

        for (t in VisitedList1) {
            if (s.compare(t)) {
                // System.out.println("yess");
                return true

            }
        }
        return false
    }

    fun isGenerated2(s: state): Boolean {

        for (t in VisitedList2) {
            if (s.compare(t)) {
                // System.out.println("yess");
                return true

            }
        }
        return false
    }

    fun CheckFring(): Boolean {
        for (f1 in fringe1) {
            for (f2 in fringe2) {
                if (f1.compare(f2)) {
                    mid1 = f1
                    mid2 = f2
                    return true
                }
            }
        }
        return false
    }


    fun print(result: Boolean) {
        println("BDS:")
        println()
        if (result) {

            while (mid1!!.parent != null) {
                path1.add(mid1!!.PathFromParent)
                mid1 = mid1!!.parent
            }
            while (mid2!!.parent != null) {
                path2.add(mid2!!.PathFromEnd)
                mid2 = mid2!!.parent
            }
            println("action : " + (path1.size + path2.size))
            println("Path:")

            for (i in path1.indices.reversed()) {
                println(path1[i])
            }
            for (i in path2.indices) {

                println(path2[i])
            }

        } else {
            println("No Soloution")
        }
        println()
        println("Number of gnarated nodes : " + (nodeCounter + 2))
        println("Max number of node in ram : " + (maxNodeCounter + 2))
        println("Time = " + (System.currentTimeMillis() - time).toFloat() / 1000.toFloat() + " Sec")
        println()
    }

}