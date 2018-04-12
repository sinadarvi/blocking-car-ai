package com.darvishi.sina.uninformedsearch

import com.darvishi.sina.Car
import com.darvishi.sina.Move
import java.util.*
import kotlin.collections.ArrayList

class BFS {

    private val visitedList = mutableListOf<ArrayList<Car>>()
    private var iteratorCount = 0
    fun ArrayList<Car>.findOneWayOut(): ArrayList<Move?> {

        val startedTime = Calendar.getInstance().timeInMillis


        val movesHaveBeenDone = ArrayList<Move?>()
        val listOfNodes = mutableListOf<Node>()

        //adding root node to list of nodes
        listOfNodes.add(Node(map = this, father = null, move = null))

        while (listOfNodes.size != 0) {
            if (listOfNodes[0].isItTheAnswer()) {
                //some work on finding parents moves and add it to 0 array list
                movesHaveBeenDone.findMoves(listOfNodes[0])
                break
            } else {
                listOfNodes.findNodes()
                listOfNodes.removeAt(0)
            }
        }
        val finishedTime = Calendar.getInstance().timeInMillis
        println("BFS Done it : ${finishedTime - startedTime} milisec")
        println("With $iteratorCount iterates")
        return movesHaveBeenDone
    }

    private fun MutableList<Node>.findNodes() {
        val currentNode = this[0]
        if(!isItVisited(this[0].map)) {
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
                            this.add(Node(newPosition, currentNode, Move(it.index, dir = 'l', howMuch = i)))
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
                            this.add(Node(newPosition, currentNode, Move(it.index, dir = 'r', howMuch = i)))
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
                            this.add(Node(newPosition, currentNode, Move(it.index, dir = 'u', howMuch = i)))
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
                            this.add(Node(newPosition, currentNode, Move(it.index, dir = 'd', howMuch = i)))
                        } else
                            break
                }
            }
            visitedList.add(currentNode.map)
        }
    }

    private fun isItVisited(cars: ArrayList<Car>): Boolean {
        visitedList.forEach {
            var count = 0
            for (i in 0 until it.size){
                if((it[i].row == cars[i].row) && (it[i].column == cars[i].column))
                    count++
            }
            if(count == it.size){
                return true
            }
        }
        return false
    }
}




