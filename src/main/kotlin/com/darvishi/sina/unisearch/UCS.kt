package com.darvishi.sina.unisearch

import com.darvishi.sina.Car
import com.darvishi.sina.Move
import java.util.*
import kotlin.collections.ArrayList


class UCS {

    private val visitedList = mutableListOf<ArrayList<Car>>()
    private var iteratorCount = 0

    fun ArrayList<Car>.findOneWayOut(): ArrayList<Move?> {

        val startedTime = Calendar.getInstance().timeInMillis
        val movesHaveBeenDone = ArrayList<Move?>()
        val comparator = Comparator<Node> { c1, c2 ->
            if(c1.cost > c2.cost)
                c1.cost
            else
                c2.cost
        }
        val listOfNodes = PriorityQueue<Node>(comparator)

        //Comparator anonymous class implementation

        //adding root node to list of nodes
        listOfNodes.add(Node(map = this, father = null, move = null))

        while (listOfNodes.size != 0) {
            val frontNode = listOfNodes.poll()
            if (frontNode.isItTheAnswer()) {
                //some work on finding parents moves and add it to 0 array list
                movesHaveBeenDone.findMoves(frontNode)
                break
            } else {
                listOfNodes.add(frontNode)
                listOfNodes.findNodes()
            }
        }
        val finishedTime = Calendar.getInstance().timeInMillis
        println("BFS Done it : ${finishedTime - startedTime} milisec")
        println("With $iteratorCount iterates")
        return movesHaveBeenDone
    }

    private fun PriorityQueue<Node>.findNodes() {
        val currentNode = this.poll()
        if(!isItVisited(currentNode.map)) {
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
                            var cost = 0
                            if(it.size == 2)
                                cost = 2
                            if(it.size == 3)
                                cost = 3
                            this.add(Node(newPosition, currentNode, Move(it.index, dir = 'l', howMuch = i),cost = cost))
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
                            var cost = 0
                            if(it.size == 2)
                                cost = 1
                            if(it.size == 3)
                                cost = 2
                            this.add(Node(newPosition, currentNode, Move(it.index, dir = 'r', howMuch = i),cost = cost))
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
                            var cost = 0
                            if(it.size == 2)
                                cost = 2
                            if(it.size == 3)
                                cost = 3
                            this.add(Node(newPosition, currentNode, Move(it.index, dir = 'u', howMuch = i),cost = cost))
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
                            var cost = 0
                            if(it.size == 2)
                                cost = 1
                            if(it.size == 3)
                                cost = 2
                            this.add(Node(newPosition, currentNode, Move(it.index, dir = 'd', howMuch = i),cost = cost))
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