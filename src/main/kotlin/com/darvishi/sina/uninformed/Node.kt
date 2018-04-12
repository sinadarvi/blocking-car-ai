package com.darvishi.sina.uninformed

import com.darvishi.sina.Car
import com.darvishi.sina.Move

class Node(val map: ArrayList<Car>, val father: Node?, val move: Move?, var depth: Int = 0, var cost:Int = 0)


fun getMatres(cars: ArrayList<Car>): Array<IntArray> {
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

fun ArrayList<Move?>.findMoves(node: Node?) {
    node?.move?.let { this.add(0, node.move) }
    node?.father?.let { this.findMoves(node.father) }
}

fun Node.isItTheAnswer(): Boolean {
    return this.map[0].column == 5
}