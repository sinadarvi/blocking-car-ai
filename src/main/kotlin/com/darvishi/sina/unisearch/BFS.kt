package com.darvishi.sina.unisearch

import com.darvishi.sina.Car
import com.darvishi.sina.Move

class BFS {
    fun ArrayList<Car>.findOneWayOut(): ArrayList<Move> {
        //do BFS
        val rootNode = Node(this, null, null)

        val movesHaveBeenDone = ArrayList<Move>()
        val listOfPossibleMoves = mutableListOf<Node>()

        val matres = getMatres(cars = this)

        listOfPossibleMoves.findMoves(rootNode, matres, cars = this)

        while (listOfPossibleMoves.size == 0) {
            if (listOfPossibleMoves[0].isItTheAnswer()) {

                break
            } else {

            }
        }

        //find matres
        //find moves can be done and add them to end of the set
        //loop till all moves done {
        //do on moves
        //is it answer -> yes : finish
        //             -> no : continue
        //find matres
        //find moves can be done and add them to end of the set }
        return movesHaveBeenDone
    }

    private fun MutableList<Node>.findMoves(fathersNode: Node, matres: Array<IntArray>, cars: ArrayList<Car>) {
        cars.forEach {
            if (it.dir == 'h') {
                for (i in 1 until it.column)
                    if (it.column - i >= 1)
                        if (matres[it.column - i][it.row] != 0) {
                            //move to left
                            val newPosition = cars.clone() as ArrayList<Car>
                            newPosition[it.index - 1].column = it.column - i
                            this.add(Node(newPosition, fathersNode, Move(it.index, 'l', i)))
                        }
                for(i in 1..(6 - (it.column + it.size)))
                if (it.column + it.size <= 6)
                    if (matres[it.column + it.size][it.row] != 0) {
                        //move to right
                        val newPosition = cars.clone() as ArrayList<Car>
                        newPosition[it.index - 1].column = it.column - it.size
                        this.add(Node(newPosition, fathersNode, Move(it.index, 'r', 1)))
                    }
            } else {
                if (it.row - 1 >= 1)
                    if (matres[it.column][it.row - 1] != 0) {
                        //move to up
                        val newPosition = cars.clone() as ArrayList<Car>
                        newPosition[it.index - 1].row = it.row - 1
                        this.add(Node(newPosition, fathersNode, Move(it.index, 'u', 1)))
                    }
                if (it.row + it.size <= 6)
                    if (matres[it.column][it.row + it.size] != 0) {
                        //move to down
                        val newPosition = cars.clone() as ArrayList<Car>
                        newPosition[it.index - 1].row = it.row + it.size
                        this.add(Node(newPosition, fathersNode, Move(it.index, 'd', 1)))
                    }
            }
        }
    }

    private fun getMatres(cars: ArrayList<Car>): Array<IntArray> {
        val matres = Array(6, { IntArray(6) })
        cars.forEach {
            when (it.dir) {
                'h' -> {
                    for (i in 0 until it.size) {
                        matres[it.column + i - 1][it.row - 1] = it.index
                    }
                }
                'v' -> {
                    for (i in 0 until it.size) {
                        matres[it.column - 1][it.row + i - 1] = it.index
                    }
                }
            }
        }
        return matres
    }
}


