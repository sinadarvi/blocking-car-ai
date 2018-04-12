package com.darvishi.sina.uninformedsearch

import com.darvishi.sina.Car
import com.darvishi.sina.Move
import java.util.*
import kotlin.collections.ArrayList

class IDS {

    private var visitedList = mutableListOf<ArrayList<Car>>()
    private var iteratorCount = 0
    fun ArrayList<Car>.findOneWayOut(): ArrayList<Move?> {

        val startedTime = Calendar.getInstance().timeInMillis


        val movesHaveBeenDone = ArrayList<Move?>()
        var depthLimit = 1

        //adding root node to list of nodes
        loop@ while (true) {
            val listOfNodes = mutableListOf<Node>()
            listOfNodes.add(Node(map = this, father = null, move = null))
            visitedList = mutableListOf()
            iteratorCount = 0

            while (listOfNodes.size != 0) {

                if (listOfNodes[0].depth < depthLimit) {
                    if (listOfNodes[0].isItTheAnswer()) {
                        //some work on finding parents moves and add it to 0 array list
                        movesHaveBeenDone.findMoves(listOfNodes[0])
                        break@loop
                    } else {
                        listOfNodes.findNodes()
                        listOfNodes.removeAt(0)
                    }
                }
                else{
                    depthLimit++
                    continue@loop
                }
            }
        }
        val finishedTime = Calendar.getInstance().timeInMillis
        println("IDS Done it : ${finishedTime - startedTime} milisec")
        println("With $iteratorCount iterates")
        return movesHaveBeenDone
    }

    private fun MutableList<Node>.findNodes() {
        val currentNode = this[0]
        if (!isItVisited(this[0].map)) {
            iteratorCount++
            val matres = getMatres(currentNode.map)
            currentNode.map.forEach {
                if (it.dir == 'h') {
                    for (i in 1 until it.column)
                        if (matres[(it.column - i) - 1][it.row - 1] == 0) {
                            //move to left
                            val newPosition = ArrayList<Car>()
                            currentNode.map.forEach { car ->
                                if (car.index != it.index)
                                    newPosition.add(Car("${car.index} ${car.row} ${car.column} ${car.dir} ${car.size}"))
                                else
                                    newPosition.add(Car("${car.index} ${car.row} ${it.column - i} ${car.dir} ${car.size}"))
                            }
                            this.add(Node(newPosition, currentNode, Move(it.index, dir = 'l', howMuch = i), currentNode.depth + 1))
                        } else
                            break
                    for (i in 1..(6 - (it.column + it.size - 1)))
                        if (matres[((it.column + it.size - 1) + i) - 1][it.row - 1] == 0) {
                            //move to right
                            val newPosition = ArrayList<Car>()
                            currentNode.map.forEach { car ->
                                if (car.index != it.index)
                                    newPosition.add(Car("${car.index} ${car.row} ${car.column} ${car.dir} ${car.size}"))
                                else
                                    newPosition.add(Car("${car.index} ${car.row} ${it.column + i} ${car.dir} ${car.size}"))
                            }
                            this.add(Node(newPosition, currentNode, Move(it.index, dir = 'r', howMuch = i), currentNode.depth + 1))
                        } else
                            break
                } else {
                    for (i in 1 until it.row)
                        if (matres[it.column - 1][(it.row - i) - 1] == 0) {
                            //move to up
                            val newPosition = ArrayList<Car>()
                            currentNode.map.forEach { car ->
                                if (car.index != it.index)
                                    newPosition.add(Car("${car.index} ${car.row} ${car.column} ${car.dir} ${car.size}"))
                                else
                                    newPosition.add(Car("${car.index} ${it.row - i} ${car.column} ${car.dir} ${car.size}"))
                            }
                            this.add(Node(newPosition, currentNode, Move(it.index, dir = 'u', howMuch = i), currentNode.depth + 1))
                        } else
                            break
                    for (i in 1..(6 - (it.row + it.size - 1)))
                        if (matres[it.column - 1][((it.row + it.size - 1) + i) - 1] == 0) {
                            //move to down
                            val newPosition = ArrayList<Car>()
                            currentNode.map.forEach { car ->
                                if (car.index != it.index)
                                    newPosition.add(Car("${car.index} ${car.row} ${car.column} ${car.dir} ${car.size}"))
                                else
                                    newPosition.add(Car("${car.index} ${it.row + i} ${car.column} ${car.dir} ${car.size}"))
                            }
                            this.add(Node(newPosition, currentNode, Move(it.index, dir = 'd', howMuch = i), currentNode.depth + 1))
                        } else
                            break
                }
            }
            visitedList.add(currentNode.map)
        }
    }

    private fun getMatres(cars: ArrayList<Car>): Array<IntArray> {
        val matres = Array(6, { IntArray(6) })
        cars.forEach {
            when (it.dir) {
                'h' -> {
                    for (i in 0 until it.size) {
                        matres[it.column - 1 + i][it.row - 1] = it.index
                    }
                }
                'v' -> {
                    for (i in 0 until it.size) {
                        matres[it.column - 1][it.row - 1 + i] = it.index
                    }
                }
            }
        }
        return matres
    }

    private fun ArrayList<Move?>.findMoves(node: Node?) {
        node?.move?.let { this.add(0, node.move) }
        node?.father?.let { this.findMoves(node.father) }
    }

    private fun Node.isItTheAnswer(): Boolean {
        return this.map[0].column == 5
    }

    private fun isItVisited(cars: ArrayList<Car>): Boolean {
        visitedList.forEach {
            var count = 0
            for (i in 0 until it.size) {
                if ((it[i].row == cars[i].row) && (it[i].column == cars[i].column))
                    count++
            }
            if (count == it.size) {
                return true
            }
        }
        return false
    }
}