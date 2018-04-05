package com.darvishi.sina

import com.darvishi.sina.unisearch.BFS

fun main(args: Array<String>) {

    val cars = ArrayList<Car>()
    var moves = ArrayList<Move>()

    //Text to Cars
    with(IO()) {
        "./sampleMap.txt" asResource {
            it.lines().forEach {
                if (it.length > 1)
                    cars.add(Car(it))
            }
        }
    }

    //Cars to Moves
    with(BFS()) {
        moves = cars.findOneWayOut()
    }

    //Moves to Text
    with(IO()) {
        moves.makeActionsFile()
    }

}



